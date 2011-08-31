package com.gemserk.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;

/**
 * Registers to ResourceManager images from a file, where each line contains id=imagefile.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PropertiesImageLoader {

	private ResourceManager resourceManager;

	private DataSourceProvider dataSourceProvider = new DataSourceProvider();

	public void setResourceManager(ResourceManager resourceManagerImpl) {
		this.resourceManager = resourceManagerImpl;
	}

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

				ImageLoader dataLoader = new ImageLoader(dataSourceProvider.get(values[0]));
				resourceManager.add(imageKey, dataLoader);
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}
}