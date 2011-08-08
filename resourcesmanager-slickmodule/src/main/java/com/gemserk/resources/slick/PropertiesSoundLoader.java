package com.gemserk.resources.slick;

import java.util.Properties;

import com.gemserk.resources.PropertiesLoader;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickSoundLoader;

@SuppressWarnings("unchecked")
public class PropertiesSoundLoader extends PropertiesBaseLoader {

	public void load(String propertiesFile) {

		// slick doesn't have sound constructor with input stream
		PropertiesLoader propertiesLoader = new PropertiesLoader(new ClassPathDataSource(propertiesFile));
		Properties properties = propertiesLoader.load();

		for (String id : properties.stringPropertyNames()) {
			String imageProperties = properties.getProperty(id);
			String[] values = imageProperties.split(",");

			String file = values[0];

//			DataLoader dataLoader = new SlickSoundLoader(file);
			DataLoader dataLoader = new SlickSoundLoader(new ClassPathDataSource(file));
			resourceManager.add(id, new CachedResourceLoader(new ResourceLoaderImpl(dataLoader)));
		}
	}
}