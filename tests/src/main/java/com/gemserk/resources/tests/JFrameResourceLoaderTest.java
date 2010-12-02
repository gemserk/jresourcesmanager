package com.gemserk.resources.tests;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.gemserk.resources.PropertiesImageLoader;
import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.java2d.FontLoaderProvider;
import com.gemserk.resources.java2d.ImageLoaderProvider;
import com.gemserk.resources.java2d.resourceloaders.DerivedFontLoader;
import com.gemserk.resources.java2d.resourceloaders.ImageResourceLoader;
import com.gemserk.resources.java2d.resourceloaders.TrueTypeFontLoader;
import com.gemserk.resources.streams.ClassPathResourceStream;
import com.gemserk.resources.streams.FileSystemResourceStream;
import com.gemserk.resources.streams.RemoteResourceStream;

public class JFrameResourceLoaderTest {

	public static void main(String[] args) {

		final ResourceManager resourceManager = new ResourceManager();

		FontLoaderProvider fontResourceProvider = new FontLoaderProvider() {

			@Override
			public ResourceLoader<Font> get(String id) {
				FontKey font = super.parseFontDescription(id);
				return new DerivedFontLoader(resourceManager, font.name, font.size, font.type);
			}

		};

		resourceManager.registerResourceLoader("ZombieRockers", new TrueTypeFontLoader(new ClassPathResourceStream("assets/fonts/Mugnuts.ttf")));
		resourceManager.registerResourceLoader("ZombieRockers2", new TrueTypeFontLoader(new FileSystemResourceStream("assets/fonts/Mugnuts.ttf")));

		PropertiesImageLoader propertiesImageLoader = new PropertiesImageLoader();
		propertiesImageLoader.setResourceManager(resourceManager);
		propertiesImageLoader.load("images.properties");

		// imagesProvider.registerResourceLoader("WhiteLogo", new ImageResourceLoader(new ClassPathResourceStream("logo-gemserk-512x116-white.png")));
		// imagesProvider.registerResourceLoader("BlackLogo", new ImageResourceLoader(new ClassPathResourceStream("logo-gemserk-512x116.png")));

		resourceManager.registerResourceLoader("BusinessCard", new ImageResourceLoader(new RemoteResourceStream("http://www.gemserk.com/things/businesscards/businesscard_front_landscape_ariel_fashionvictim_1.png")));
		// imagesProvider.registerResourceLoader("BusinessCard", new ImageResourceLoader(new RemoteResourceStream("classpath://logo-gemserk-512x116.png")));

		resourceManager.registerResourceLoader("GreenLogo", new ResourceLoader<Image>() {

			@Override
			public Image load() {
				Resource<Image> whiteLogoImageResource = resourceManager.get("WhiteLogo", Image.class);

				Image image = whiteLogoImageResource.get();

				BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

				Graphics graphics = bufferedImage.getGraphics();

				graphics.drawImage(image, 0, 0, null);

				for (int i = 0; i < bufferedImage.getWidth(); i++) {
					for (int j = 0; j < bufferedImage.getHeight(); j++) {
						int rgb = bufferedImage.getRGB(i, j);
						if (rgb >= 0xaaaaaa) {
							bufferedImage.setRGB(i, j, Color.green.getRGB());
						}
					}
				}

				return bufferedImage;
			}
		});

		resourceManager.registerLoaderProvider(Font.class, fontResourceProvider);
		resourceManager.registerLoaderProvider(Image.class, new ImageLoaderProvider());

		JFrame frame = new JFrame() {
			{

				setSize(1024, 768);
				setVisible(true);

				setLayout(new GridLayout(2, 1));

				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});

				add(new JButton() {
					{
						Resource<Font> fontResource = resourceManager.get("ZombieRockers,3,48", Font.class);
						final Resource<Image> imageResource = resourceManager.get("BusinessCard", Image.class);
						final Resource<Image> whiteLogoImagerResource = resourceManager.get("GreenLogo", Image.class);

						setText("Change Color!");
						setFont(fontResource.get());

						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// System.exit(0);
								imageResource.set(whiteLogoImagerResource.get());
								getContentPane().repaint();
							}
						});

					}
				});

				add(new JButton() {
					{
						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								resourceManager.reload("BusinessCard");
								// resourceManager.reload("BlackLogo");
							}
						});
					}

					Resource<Image> imageResource = resourceManager.get("BusinessCard", Image.class);

					public void paint(Graphics g) {
						super.paint(g);
						Image image = imageResource.get();
						g.drawImage(image, getWidth() / 2 - image.getWidth(null) / 2, getHeight() / 2 - image.getHeight(null) / 2, null);
					};

				});

			}
		};

		frame.setVisible(true);
		frame.repaint();
	}

}
