package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

import com.gemserk.resources.dataloaders.DataLoader;

public class SlickAngelCodeFontLoader extends DataLoader<Font> {

	private String fntFile;
	
	private String imgFile;

	public SlickAngelCodeFontLoader(String fntFile, String imgFile) {
		this.fntFile = fntFile;
		this.imgFile = imgFile;

	}

	@Override
	public Font load() {
		try {
			return new AngelCodeFont(fntFile, imgFile);
		} catch (SlickException e) {
			throw new RuntimeException("failed to load angel code font ", e);
		}
	}

}