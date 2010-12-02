package com.gemserk.resources.slick;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ReloadableResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class PropertiesImageLoader {

	ResourceManager resourceManager;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void load(String imagePropertiesFile) {
		try {
			Properties imagesMap = new Properties();
			InputStream imageMapStream = new ClassPathDataSource(imagePropertiesFile).getInputStream();
			imagesMap.load(imageMapStream);

			for (String imageKey : imagesMap.stringPropertyNames()) {
				String imageProperties = imagesMap.getProperty(imageKey);
				String[] values = imageProperties.split(",");

				SlickImageLoader dataLoader = new SlickImageLoader(values[0]);
				resourceManager.add(imageKey, new CachedResourceLoader(new ReloadableResourceLoaderImpl(dataLoader)));
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}
}