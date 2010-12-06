package com.gemserk.resources.tests;

import java.awt.Font;
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
import com.gemserk.resources.ResourcesBuilder;
import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.datasources.DataSourceProvider.Source;

public class ResourcesBuilderSample {

	public static void main(String[] args) {

		final ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		ResourcesBuilder resourcesBuilder = new ResourcesBuilder(resourceManager, new DataSourceProvider(Source.ClassPath)) {
			{
				java2d.image("CompanyLogo", "logo-gemserk-512x116.png");
				java2d.font("ZombieRockers48px", "assets/fonts/Mugnuts.ttf", Font.PLAIN, 48);
			}
		};

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
					Resource<Font> fontResource = resourceManager.get("ZombieRockers48px");
					Resource<Image> imageResource = resourceManager.get("CompanyLogo");
					{
						setText("Click to exit!");
						setFont(fontResource.get());

						addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
							}
						});

					}
					public void paint(java.awt.Graphics g) {
						Image image = imageResource.get();
						g.drawImage(image, getWidth() / 2 - image.getWidth(null) / 2, getHeight() / 2 - image.getHeight(null) / 2, null);
						g.setFont(fontResource.get());
						g.drawString("Click to exit!", 340, 200);
					};
					
				});

			}
		};

		frame.setVisible(true);
		frame.repaint();
	}

}
