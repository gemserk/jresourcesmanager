package com.gemserk.resources.slick2d;

import java.util.HashMap;

import org.newdawn.slick.Image;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceLoaderProvider;
import com.gemserk.resources.slick2d.resourceloaders.SlickImageResourceLoader;

public class ImageLoaderProvider extends ResourceLoaderProvider<String, Image> {

	public ImageLoaderProvider() {

	}

	public ImageLoaderProvider(HashMap<String, String> files) {
		for (String id : files.keySet()) {
			String file = files.get(id);
			registerResourceLoader(id, new SlickImageResourceLoader(file));
		}
	}

	@Override
	protected ResourceLoader<Image> getDefaultLoader(String id) {
		ResourceLoader<Image> resourceLoader = new SlickImageResourceLoader(id);
		registerResourceLoader(id, resourceLoader);
		return resourceLoader;
	}

}