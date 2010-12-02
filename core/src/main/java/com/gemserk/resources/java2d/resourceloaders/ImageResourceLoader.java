package com.gemserk.resources.java2d.resourceloaders;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.streams.ResourceStream;

public class ImageResourceLoader extends ResourceLoader<Image> {
	
	protected static final Logger logger = LoggerFactory.getLogger(ImageResourceLoader.class);

	private final ResourceStream resourceStream;

	public ImageResourceLoader(ResourceStream resourceStream) {
		this.resourceStream = resourceStream;
	}

	@Override
	public Image load() {
		try {
			BufferedImage image = ImageIO.read(resourceStream.getInputStream());
			if (logger.isInfoEnabled())
				logger.info("Image " + resourceStream.getResourceName() + " loaded.");
			return image;
		} catch (IOException e) {
			throw new RuntimeException("Failed to read resource for " + resourceStream.getResourceName(), e);
		}
	}

}