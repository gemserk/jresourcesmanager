package com.gemserk.resources.java2d;

import java.awt.Image;
import java.util.HashMap;
import java.util.List;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceLoaderProvider;
import com.gemserk.resources.java2d.resourceloaders.ImageResourceLoader;
import com.gemserk.resources.streams.ClassPathResourceStream;

public class ImageLoaderProvider extends ResourceLoaderProvider<String, Image> {

	public ImageLoaderProvider() {

	}

	public ImageLoaderProvider(List<String> registeredImages) {
		for (String file : registeredImages)
			registerResourceLoader(file, new ImageResourceLoader(new ClassPathResourceStream(file)));
	}

	public ImageLoaderProvider(HashMap<String, String> files) {
		for (String id : files.keySet()) {
			String file = files.get(id);
			registerResourceLoader(id, new ImageResourceLoader(new ClassPathResourceStream(file)));
		}
	}

	@Override
	protected ResourceLoader<Image> getDefaultLoader(String id) {
		// if it starts with classpath://
		ImageResourceLoader resourceLoader = new ImageResourceLoader(new ClassPathResourceStream(id));
		registerResourceLoader(id, resourceLoader);
		return resourceLoader;
	}

}