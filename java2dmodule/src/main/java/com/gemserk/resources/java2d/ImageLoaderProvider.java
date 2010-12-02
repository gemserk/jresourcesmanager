package com.gemserk.resources.java2d;

import java.awt.Image;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceLoaderProvider;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.java2d.resourceloaders.ImageResourceLoader;

public class ImageLoaderProvider extends ResourceLoaderProvider<String, Image> {

	@Override
	public ResourceLoader<Image> get(String id) {
		return new ImageResourceLoader(new ClassPathDataSource(id));
	}

}