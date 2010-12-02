package com.gemserk.resources.java2d.resourceloaders;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.datasources.ResourceDataSource;

public class ImageResourceLoader extends ResourceLoader<Image> {
	
	protected static final Logger logger = LoggerFactory.getLogger(ImageResourceLoader.class);

	private final ResourceDataSource resourceDataSource;

	public ImageResourceLoader(ResourceDataSource resourceDataSource) {
		this.resourceDataSource = resourceDataSource;
	}

	@Override
	public Image load() {
		try {
			BufferedImage image = ImageIO.read(resourceDataSource.getInputStream());
			if (logger.isInfoEnabled())
				logger.info("Image " + resourceDataSource.getResourceName() + " loaded.");
			return image;
		} catch (IOException e) {
			throw new RuntimeException("Failed to read resource for " + resourceDataSource.getResourceName(), e);
		}
	}

}