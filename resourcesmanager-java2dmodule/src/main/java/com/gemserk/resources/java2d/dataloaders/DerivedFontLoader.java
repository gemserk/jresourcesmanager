package com.gemserk.resources.java2d.dataloaders;

import java.awt.Font;

import com.gemserk.resources.Resource;
import com.gemserk.resources.dataloaders.DataLoader;

public class DerivedFontLoader extends DataLoader<Font> {

	private final float size;

	private final int sytle;

	private final Resource<Font> fontResource;

	public DerivedFontLoader(Resource<Font> fontResource, float size, int style) {
		this.fontResource = fontResource;
		this.size = size;
		this.sytle = style;
	}

	@Override
	public Font load() {
		return fontResource.get().deriveFont(size).deriveFont(sytle);
	}
}