package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class SlickImageLoader extends DataLoader<Image> {

	protected static final Logger logger = LoggerFactory.getLogger(SlickImageLoader.class);

	private final DataSource dataSource;

	public SlickImageLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Image load() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Loading slick image from file " + dataSource.getResourceName());
			return new Image(dataSource.getInputStream(), dataSource.getResourceName(), false);
		} catch (SlickException e) {
			if (logger.isErrorEnabled())
				logger.error("Failed to load slick image from file " + dataSource.getResourceName());
			throw new RuntimeException("Failed to load slick image from file " + dataSource.getResourceName(), e);
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