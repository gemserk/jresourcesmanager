package com.gemserk.resources.slick;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.ResourceManagerOld;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;
import com.google.inject.Inject;

public class PropertiesImageLoader {

	ResourceManagerOld resourceManagerImpl;

	@Inject
	public void setResourceManager(ResourceManagerOld resourceManagerImpl) {
		this.resourceManagerImpl = resourceManagerImpl;
	}

	public void load(String imagePropertiesFile) {
		try {
			Properties imagesMap = new Properties();
			InputStream imageMapStream = new ClassPathDataSource(imagePropertiesFile).getInputStream();
			imagesMap.load(imageMapStream);

			for (String imageKey : imagesMap.stringPropertyNames()) {
				String imageProperties = imagesMap.getProperty(imageKey);
				String[] values = imageProperties.split(",");
				resourceManagerImpl.registerDataLoader(imageKey, new SlickImageLoader(values[0]));
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}
}