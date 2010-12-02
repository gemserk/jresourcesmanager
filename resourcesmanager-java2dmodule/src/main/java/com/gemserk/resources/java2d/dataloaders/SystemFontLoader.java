package com.gemserk.resources.java2d.dataloaders;

import java.awt.Font;

import com.gemserk.resources.FontKey;
import com.gemserk.resources.dataloaders.DataLoader;

/**
 * Loads a java.awt.Font specified by an id formed by [fontName,fontStyle,fontSize].
 */
public class SystemFontLoader extends DataLoader<Font> {

	private final String id;

	public SystemFontLoader(String id) {
		this.id = id;
	}

	@Override
	public Font load() {
		FontKey fontKey = FontKey.parseFontDescription(id);
		return new Font(fontKey.name, fontKey.type, fontKey.size);
	}
}