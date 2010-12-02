package com.gemserk.resources.java2d;

import java.awt.Image;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceLoaderProvider;
import com.gemserk.resources.java2d.resourceloaders.ImageResourceLoader;
import com.gemserk.resources.streams.ClassPathResourceStream;

public class ImageLoaderProvider extends ResourceLoaderProvider<String, Image> {

	@Override
	public ResourceLoader<Image> get(String id) {
		return new ImageResourceLoader(new ClassPathResourceStream(id));
	}

}