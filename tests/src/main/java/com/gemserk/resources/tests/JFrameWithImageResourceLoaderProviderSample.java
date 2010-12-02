package com.gemserk.resources.tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;
import com.gemserk.resources.resourceloaders.ReloadableResourceLoaderImpl;

public class JFrameWithImageResourceLoaderProviderSample {

	public static void main(String[] args) {

		final ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		// registers a loader which tries to load an image by using the resource "id" as a file in the classpath
		// resourceManagerImpl.registerLoaderProvider(Image.class, new ImageLoaderProvider(new DataSourceProvider(Source.ClassPath)));

		resourceManager.add("BusinessCard", new ReloadableResourceLoaderImpl<Image>(new ImageLoader(new ClassPathDataSource("assets/images/businesscard_front_landscape_ariel_fashionvictim_1.png"))));

		JFrame frame = new JFrame() {
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
					{
						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
							}
						});
					}

					Resource<Image> imageResource = resourceManager.get("BusinessCard");

					public void paint(Graphics g) {
						super.paint(g);
						g.setColor(Color.gray);
						g.fillRect(0, 0, getWidth(), getHeight());

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
