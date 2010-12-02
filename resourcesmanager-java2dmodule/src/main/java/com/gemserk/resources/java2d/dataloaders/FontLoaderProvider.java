package com.gemserk.resources.java2d.dataloaders;

import java.awt.Font;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.DataLoaderProvider;

public class FontLoaderProvider extends DataLoaderProvider<String, Font> {

	@Override
	public DataLoader<Font> get(final String id) {
		return new SystemFontLoader(id);
	}

}