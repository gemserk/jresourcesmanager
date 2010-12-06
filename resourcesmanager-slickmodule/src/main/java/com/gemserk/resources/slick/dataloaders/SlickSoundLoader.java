package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.gemserk.resources.dataloaders.DataLoader;

public class SlickSoundLoader extends DataLoader<Sound> {

	private final String file;

	public SlickSoundLoader(String file) {
		this.file = file;
	}

	@Override
	public Sound load() {
		try {
			return new Sound(file);
		} catch (SlickException e) {
			throw new RuntimeException(e);
		}
	}
}