package com.gemserk.resources.slick;

import org.newdawn.slick.Image;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceLoaderProvider;
import com.gemserk.resources.slick.resourceloaders.SlickImageResourceLoader;

public class ImageLoaderProvider extends ResourceLoaderProvider<String, Image> {

	@Override
	public ResourceLoader<Image> get(String id) {
		return new SlickImageResourceLoader(id);
	}

}