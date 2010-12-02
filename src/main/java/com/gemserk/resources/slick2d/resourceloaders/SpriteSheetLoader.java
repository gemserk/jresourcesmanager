package com.gemserk.resources.slick2d.resourceloaders;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceManager;

public class SpriteSheetLoader extends ResourceLoader<SpriteSheet> {

	private final ResourceManager resourceManager;

	private final Object imageResourceId;

	private final int th;

	private final int tw;

	public SpriteSheetLoader(ResourceManager resourceManager, Object imageResourceId, int th, int tw) {
		this.resourceManager = resourceManager;
		this.imageResourceId = imageResourceId;
		this.th = th;
		this.tw = tw;
	}

	@Override
	public SpriteSheet load() {
		Resource<Image> image = resourceManager.get(imageResourceId, Image.class);
		return new SpriteSheet(image.get(), tw, th);
	}
}