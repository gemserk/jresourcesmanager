package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import com.gemserk.resources.dataloaders.DataLoader;

public class SlickUnicodeFontLoader extends DataLoader<Font> {
	
	String ttfFileRef;
	
	String hieroFileRef;
	
	public SlickUnicodeFontLoader(String ttfFileRef, String hieroFileRef) {
		this.ttfFileRef = ttfFileRef;
		this.hieroFileRef = hieroFileRef;
	}

	public Font load() {
		try {
			UnicodeFont unicodeFont = new UnicodeFont(ttfFileRef, hieroFileRef);
			unicodeFont.addAsciiGlyphs();
			unicodeFont.loadGlyphs();
			return unicodeFont;
		} catch (SlickException e) {
			throw new RuntimeException(e);
		}
	}
}