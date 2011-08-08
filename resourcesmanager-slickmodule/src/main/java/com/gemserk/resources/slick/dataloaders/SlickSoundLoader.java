package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class SlickSoundLoader extends DataLoader<Sound> {

	protected static final Logger logger = LoggerFactory.getLogger(SlickSoundLoader.class);

	private final DataSource dataSource;

	public SlickSoundLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Sound load() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Loading slick sound from file " + dataSource.getResourceName());
			return new Sound(dataSource.getInputStream(), dataSource.getResourceName());
		} catch (SlickException e) {
			if (logger.isErrorEnabled())
				logger.error("failed to load slick sound from file " + dataSource.getResourceName(), e);
			throw new RuntimeException("failed to load slick sound from file " + dataSource.getResourceName(), e);
		}
	}
	
}