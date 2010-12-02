package com.gemserk.resources.tests;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.datasources.FileSystemDataSource;
import com.gemserk.resources.java2d.dataloaders.DerivedFontLoader;
import com.gemserk.resources.java2d.dataloaders.TrueTypeFontLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;

public class JFrameCustomFontResourceLoaderSample {

	public static void main(String[] args) {

		final ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("ZombieRockers", new ResourceLoaderImpl<Font>(new TrueTypeFontLoader(new FileSystemDataSource("assets/fonts/Mugnuts.ttf")), true));
		Resource<Font> fontResource = resourceManager.get("ZombieRockers");
		resourceManager.add("ZombieRockersPlain48px", new ResourceLoaderImpl<Font>(new DerivedFontLoader(fontResource, 48, Font.PLAIN)));

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
						Resource<Font> fontResource = resourceManager.get("ZombieRockersPlain48px");

						setText("Click to exit!");
						setFont(fontResource.get());

						addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
							}
						});

					}
				});

			}
		};

		frame.setVisible(true);
		frame.repaint();
	}

}
