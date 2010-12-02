package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Font;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.DataLoaderProvider;

public class DefaultSlickFontLoaderProvider extends DataLoaderProvider<String, Font> {
	
	static class FontKey {

		final String name;

		final int type;

		final int size;

		FontKey(String name, int type, int size) {
			this.name = name;
			this.type = type;
			this.size = size;
		}

		static FontKey parseFontDescription(String fontDescription) {
			String[] values = fontDescription.split(",");
			return new FontKey(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
		}
	}
	
	@Override
	public DataLoader<Font> get(String id) {
		FontKey font = FontKey.parseFontDescription(id);
		return new SlickTrueTypeFontLoader(font.name, font.type, font.size);
	}
}