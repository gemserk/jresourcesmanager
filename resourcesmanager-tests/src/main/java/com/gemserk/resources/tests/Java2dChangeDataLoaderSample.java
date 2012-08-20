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

import javax.swing.JButton;
import javax.swing.JFrame;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;

public class Java2dChangeDataLoaderSample {

	public static void main(String[] args) {

		/*
		 * In this example the DataLoader of the Resource is changed dynamically and then when the resource is reloaded, the new data is loaded from the new DataLoader. The problem with this approach is DataLoaders must be same implementation, to use same dispose method implementation when reloading the Resource.
		 */

		final ImageLoader whiteLogoImageLoader = new ImageLoader(new ClassPathDataSource("logo-gemserk-512x116-white.png"));
		final ImageLoader blackLogoImageLoader = new ImageLoader(new ClassPathDataSource("logo-gemserk-512x116.png"));

		ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("WHITE", whiteLogoImageLoader);
		resourceManager.add("BLACK", blackLogoImageLoader);

		final Resource<Image> imagerResource = resourceManager.get("WHITE");

		JFrame frame = new JFrame() {

			private static final long serialVersionUID = -6898015655555959609L;

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
					private static final long serialVersionUID = 1L;

					{
						setText("Change Color!");
						setFont(new Font("arial", Font.PLAIN, 48));

						addActionListener(new ActionListener() {

							boolean changed = false;

							@Override
							public void actionPerformed(ActionEvent e) {
								if (!changed) {
									imagerResource.setDataLoader(blackLogoImageLoader);
									setText("Change Color back!");
								} else {
									imagerResource.setDataLoader(whiteLogoImageLoader);
									setText("Change Color!");
								}
								imagerResource.reload();
								getContentPane().repaint();
								changed = !changed;
							}
						});

					}
				});

				add(new JButton() {
					private static final long serialVersionUID = 1L;

					{
						addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
							}
						});
					}

					public void paint(Graphics g) {
						super.paint(g);
						g.setColor(Color.gray);
						g.fillRect(0, 0, getWidth(), getHeight());

						Image image = imagerResource.get();
						g.drawImage(image, getWidth() / 2 - image.getWidth(null) / 2, getHeight() / 2 - image.getHeight(null) / 2, null);
					};

				});

			}
		};

		frame.setVisible(true);
		frame.repaint();
	}

}
