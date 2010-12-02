package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Image;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.DataLoaderProvider;

public class ImageLoaderProvider extends DataLoaderProvider<String, Image> {

	@Override
	public DataLoader<Image> get(String id) {
		return new SlickImageLoader(id);
	}

}