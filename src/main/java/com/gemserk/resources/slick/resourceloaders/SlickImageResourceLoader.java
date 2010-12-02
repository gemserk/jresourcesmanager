package com.gemserk.resources.slick.resourceloaders;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.ResourceLoader;

public class SlickImageResourceLoader extends ResourceLoader<Image> {

	protected static final Logger logger = LoggerFactory.getLogger(SlickImageResourceLoader.class);

	private final String file;

	public SlickImageResourceLoader(String file) {
		this.file = file;
	}

	@Override
	public Image load() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Loading slick image file " + file);
			return new Image(file);
		} catch (SlickException e) {
			throw new RuntimeException(e);
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