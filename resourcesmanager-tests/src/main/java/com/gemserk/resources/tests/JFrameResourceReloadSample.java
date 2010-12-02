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
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;

public class JFrameResourceReloadSample {

	public static void main(String[] args) {

		ImageLoader whiteLogoImageLoader = new ImageLoader(new ClassPathDataSource("logo-gemserk-512x116-white.png"));
		ImageLoader blackLogoImageLoader = new ImageLoader(new ClassPathDataSource("logo-gemserk-512x116.png"));

		final Resource<Image> whiteLogoImagerResource = new ResourceLoaderImpl<Image>(whiteLogoImageLoader).load();
		final Resource<Image> blackLogoImageResource = new ResourceLoaderImpl<Image>(blackLogoImageLoader).load();

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
						setText("Change Color!");
						setFont(new Font("arial", Font.PLAIN, 48));

						addActionListener(new ActionListener() {

							boolean changed = false;

							@Override
							public void actionPerformed(ActionEvent e) {
								if (!changed) {
									blackLogoImageResource.set(whiteLogoImagerResource.get());
									getContentPane().repaint();
									setText("Change Color back!");
								} else {
									blackLogoImageResource.reload();
									getContentPane().repaint();
									setText("Change Color!");
								}
								changed = !changed;
							}
						});

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

					public void paint(Graphics g) {
						super.paint(g);
						g.setColor(Color.gray);
						g.fillRect(0, 0, getWidth(), getHeight());

						Image image = blackLogoImageResource.get();
						g.drawImage(image, getWidth() / 2 - image.getWidth(null) / 2, getHeight() / 2 - image.getHeight(null) / 2, null);
					};

				});

			}
		};

		frame.setVisible(true);
		frame.repaint();
	}

}
