package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;

public class SlickMusicLoader extends DataLoader<Music> {

	protected static final Logger logger = LoggerFactory.getLogger(SlickMusicLoader.class);

	private final String file;

	public SlickMusicLoader(String file) {
		this.file = file;
	}

	@Override
	public Music load() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Loading slick music from file " + file);
			return new Music(file);
		} catch (SlickException e) {
			if (logger.isErrorEnabled())
				logger.error("failed to load slick music from file " + file, e);
			throw new RuntimeException("failed to load slick music from file " + file, e);
		}
	}
	
}