package com.gemserk.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.java2d.resourceloaders.ImageResourceLoader;
import com.gemserk.resources.streams.ClassPathResourceStream;
import com.google.inject.Inject;

public class PropertiesImageLoader {

	ResourceManager resourceManager;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void load(String imagePropertiesFile) {
		try {
			Properties imagesMap = new Properties();
			InputStream imageMapStream = new ClassPathResourceStream(imagePropertiesFile).getInputStream();
			imagesMap.load(imageMapStream);

			for (String imageKey : imagesMap.stringPropertyNames()) {
				String imageProperties = imagesMap.getProperty(imageKey);
				String[] values = imageProperties.split(",");
				resourceManager.registerResourceLoader(imageKey, new ImageResourceLoader(new ClassPathResourceStream(values[0])));
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}
}