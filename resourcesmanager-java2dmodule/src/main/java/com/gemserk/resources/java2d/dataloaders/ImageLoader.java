package com.gemserk.resources.java2d.dataloaders;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class ImageLoader extends DataLoader<Image> {
	
	protected static final Logger logger = LoggerFactory.getLogger(ImageLoader.class);

	private final DataSource dataSource;

	public ImageLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Image load() {
		try {
			BufferedImage image = ImageIO.read(dataSource.getInputStream());
			if (logger.isInfoEnabled())
				logger.info("Image " + dataSource.getResourceName() + " loaded.");
			return image;
		} catch (IOException e) {
			throw new RuntimeException("Failed to read resource for " + dataSource.getResourceName(), e);
		}
	}

}