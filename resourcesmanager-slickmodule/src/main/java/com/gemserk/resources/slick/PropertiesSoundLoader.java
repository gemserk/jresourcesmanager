package com.gemserk.resources.slick;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickSoundLoader;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class PropertiesSoundLoader {

	ResourceManager resourceManager;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void load(String propertiesFile) {
		try {
			Properties properties = new Properties();
			InputStream propertiesInputStream = new ClassPathDataSource(propertiesFile).getInputStream();
			properties.load(propertiesInputStream);

			for (String imageKey : properties.stringPropertyNames()) {
				String imageProperties = properties.getProperty(imageKey);
				String[] values = imageProperties.split(",");
				
				DataLoader dataLoader = new SlickSoundLoader(values[0]);
				resourceManager.add(imageKey, new CachedResourceLoader(new ResourceLoaderImpl(dataLoader)));
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}
}