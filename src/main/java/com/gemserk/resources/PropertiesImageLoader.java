package com.gemserk.resources;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.java2d.resourceloaders.ImageResourceLoader;
import com.gemserk.resources.streams.ClassPathResourceStream;
import com.google.inject.Inject;

public class PropertiesImageLoader {

	final String imagePropertiesFile;

	ResourceManager resourceManager;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	ResourceLoaderProvider<String, Image> imageResourceLoaderProvider;
	
	@Inject
	public void setImageResourceLoaderProvider(ResourceLoaderProvider<String, Image> imageResourceLoaderProvider) {
		this.imageResourceLoaderProvider = imageResourceLoaderProvider;
	}

	public PropertiesImageLoader(String imagePropertiesFile) {
		this.imagePropertiesFile = imagePropertiesFile;
	}

	public void load() {
		try {
			Properties imagesMap = new Properties();
//			InputStream imageMapStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(imagePropertiesFile);
			InputStream imageMapStream = new ClassPathResourceStream(imagePropertiesFile).getInputStream();
			imagesMap.load(imageMapStream);

			for (String imageKey : imagesMap.stringPropertyNames()) {
				String imageProperties = imagesMap.getProperty(imageKey);
				String[] values = imageProperties.split(",");
				imageResourceLoaderProvider.registerResourceLoader(imageKey, new ImageResourceLoader(new ClassPathResourceStream(values[0])));
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}

}