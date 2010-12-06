package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;

public class SlickSoundLoader extends DataLoader<Sound> {

	protected static final Logger logger = LoggerFactory.getLogger(SlickSoundLoader.class);

	private final String file;

	public SlickSoundLoader(String file) {
		this.file = file;
	}

	@Override
	public Sound load() {
		try {
			if (logger.isInfoEnabled())
				logger.info("Loading slick sound from file " + file);
			return new Sound(file);
		} catch (SlickException e) {
			if (logger.isErrorEnabled())
				logger.error("failed to load slick sound from file " + file, e);
			throw new RuntimeException("failed to load slick sound from file " + file, e);
		}
	}
	
}