package com.gemserk.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;
import com.google.inject.Inject;

/**
 * Registers to ResourceManager images from a file, where each line contains id=imagefile.
 */
public class PropertiesImageLoader {

	ResourceManagerOld resourceManagerImpl;

	DataSourceProvider dataSourceProvider = new DataSourceProvider();

	@Inject
	public void setResourceManager(ResourceManagerOld resourceManagerImpl) {
		this.resourceManagerImpl = resourceManagerImpl;
	}

	@Inject
	public void setDataSourceProvider(DataSourceProvider dataSourceProvider) {
		this.dataSourceProvider = dataSourceProvider;
	}

	public void load(String imagePropertiesFile) {
		try {
			Properties imagesMap = new Properties();
			InputStream imageMapStream = dataSourceProvider.get(imagePropertiesFile).getInputStream();
			imagesMap.load(imageMapStream);

			for (String imageKey : imagesMap.stringPropertyNames()) {
				String imageProperties = imagesMap.getProperty(imageKey);
				String[] values = imageProperties.split(",");
				resourceManagerImpl.registerDataLoader(imageKey, new ImageLoader(dataSourceProvider.get(values[0])));
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}
}