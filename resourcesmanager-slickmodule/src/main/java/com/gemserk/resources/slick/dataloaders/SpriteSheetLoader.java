package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.gemserk.resources.Resource;
import com.gemserk.resources.dataloaders.DataLoader;

public class SpriteSheetLoader extends DataLoader<SpriteSheet> {

	private final int tw;

	private final int th;
	
	private Resource<Image> imageResource;

	public SpriteSheetLoader(Resource<Image> imageResource, int tw, int th) {
		this.imageResource = imageResource;
		this.tw = tw;
		this.th = th;
	}

	@Override
	public SpriteSheet load() {
		return new SpriteSheet(imageResource.get(), tw, th);
	}
}