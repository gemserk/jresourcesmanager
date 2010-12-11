package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;

public class SlickImageLoader extends DataLoader<Image> {

	protected static final Logger logger = LoggerFactory.getLogger(SlickImageLoader.class);

	private final String file;

	public SlickImageLoader(String file) {
		this.file = file;
	}

	@Override
	public Image load() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Loading slick image from file " + file);
			return new Image(new ClassPathDataSource(file).getInputStream(), file, false);
		} catch (SlickException e) {
			if (logger.isErrorEnabled())
				logger.error("Failed to load slick image from file " + file);
			throw new RuntimeException("Failed to load slick image from file " + file, e);
		}
	}

	@Override
	public void dispose(Image image) {
		try {
			image.destroy();
		} catch (SlickException e) {
			throw new RuntimeException(e);
		}
	}
}