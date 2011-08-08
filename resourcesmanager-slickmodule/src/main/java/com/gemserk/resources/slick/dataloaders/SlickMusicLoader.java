package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class SlickMusicLoader extends DataLoader<Music> {

	protected static final Logger logger = LoggerFactory.getLogger(SlickMusicLoader.class);

	private final DataSource dataSource;

	public SlickMusicLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Music load() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Loading slick music from file " + dataSource.getResourceName());
			return new Music(dataSource.getInputStream(), dataSource.getResourceName());
		} catch (SlickException e) {
			if (logger.isErrorEnabled())
				logger.error("failed to load slick music from file " + dataSource.getResourceName(), e);
			throw new RuntimeException("failed to load slick music from file " + dataSource.getResourceName(), e);
		}
	}
	
}