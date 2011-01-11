package com.gemserk.resources.java2d.dataloaders;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class ImageLoader extends DataLoader<Image> {
	
	private final DataSource dataSource;

	public ImageLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Image load() {
		try {
			return ImageIO.read(dataSource.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("Failed to read resource for " + dataSource.getResourceName(), e);
		}
	}

}