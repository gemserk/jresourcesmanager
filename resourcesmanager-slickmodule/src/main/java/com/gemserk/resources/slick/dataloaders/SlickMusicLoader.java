package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import com.gemserk.resources.dataloaders.DataLoader;

public class SlickMusicLoader extends DataLoader<Music> {

	private final String file;

	public SlickMusicLoader(String file) {
		this.file = file;
	}

	@Override
	public Music load() {
		try {
			return new Music(file);
		} catch (SlickException e) {
			throw new RuntimeException("failed to load slick music from file " + file, e);
		}
	}
	
}