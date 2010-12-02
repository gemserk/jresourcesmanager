package com.gemserk.resources.java2d;

import java.awt.Font;

import com.gemserk.resources.DataLoaderProvider;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.java2d.dataloaders.SystemFontLoader;

public class FontLoaderProvider extends DataLoaderProvider<String, Font> {

	@Override
	public DataLoader<Font> get(final String id) {
		return new SystemFontLoader(id);
	}

}