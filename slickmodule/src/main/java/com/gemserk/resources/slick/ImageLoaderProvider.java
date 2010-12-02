package com.gemserk.resources.slick;

import org.newdawn.slick.Image;

import com.gemserk.resources.DataLoaderProvider;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;

public class ImageLoaderProvider extends DataLoaderProvider<String, Image> {

	@Override
	public DataLoader<Image> get(String id) {
		return new SlickImageLoader(id);
	}

}