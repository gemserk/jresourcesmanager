package com.gemserk.resources.java2d.resourceloaders;

import java.awt.Font;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceManager;

public class DerivedFontLoader extends ResourceLoader<Font> {

	private final ResourceManager resourceManager;

	private final String sourceFontResourceId;

	private final float size;

	private final int type;

	public DerivedFontLoader(ResourceManager resourceManager, String sourceFontResourceId, float size, int type) {
		this.resourceManager = resourceManager;
		this.sourceFontResourceId = sourceFontResourceId;
		this.size = size;
		this.type = type;
	}

	@Override
	public Font load() {
		Resource<Font> fontResource = resourceManager.get(sourceFontResourceId, Font.class);
		return fontResource.get().deriveFont(size).deriveFont(type);
	}
}