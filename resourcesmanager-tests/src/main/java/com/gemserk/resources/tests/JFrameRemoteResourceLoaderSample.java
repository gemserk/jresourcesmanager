package com.gemserk.resources.tests;

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
import com.gemserk.resources.datasources.RemoteDataSource;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;

public class JFrameRemoteResourceLoaderSample {

	public static void main(String[] args) {

		final ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		// loads a resource from a remote source

		resourceManager.add("BusinessCard", new ImageLoader(new RemoteDataSource("http://www.gemserk.com/things/businesscards/businesscard_front_landscape_ariel_fashionvictim_1.png")));

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
					Resource<Image> imageResource = resourceManager.get("BusinessCard");
					
					{
						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								imageResource.reload();
							}
						});
					}

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
