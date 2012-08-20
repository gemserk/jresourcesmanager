package com.gemserk.resources.tests;

import java.awt.Color;
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

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;

@SuppressWarnings("unchecked")
public class JFrameCustomResourceLoaderSample {

	static class CustomGreenLogo extends DataLoader<Image> {

		Resource<Image> whiteLogoImageResource;

		CustomGreenLogo(@SuppressWarnings("rawtypes") Resource whiteLogoImageResource) {
			this.whiteLogoImageResource = whiteLogoImageResource;
		}

		@Override
		public Image load() {

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
	}

	public static void main(String[] args) {

		final ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("WhiteLogo", new ImageLoader(new ClassPathDataSource("logo-gemserk-512x116-white.png")));
		// register a loader for a custom resource which uses the WhiteLogo and creates a new image with green pixels instead white.
		resourceManager.add("GreenLogo", new CustomGreenLogo(resourceManager.get("WhiteLogo")));

		JFrame frame = new JFrame() {
			private static final long serialVersionUID = 5404072814427863051L;
			{

				setSize(1024, 768);
				setVisible(true);

				setLayout(new GridLayout(1, 1));

				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});

				add(new JButton() {
					private static final long serialVersionUID = 3206855085940890631L;
					{
						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
							}
						});
					}

					Resource<Image> imageResource = resourceManager.get("GreenLogo");

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
