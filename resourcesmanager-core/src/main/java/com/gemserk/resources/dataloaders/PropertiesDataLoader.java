package com.gemserk.resources.dataloaders;

import java.io.IOException;
import java.util.Properties;

import com.gemserk.resources.datasources.DataSource;

/**
 * Create a Properties from a data source input stream.
 */
public class PropertiesDataLoader extends DataLoader<Properties> {
	
	private final DataSource dataSource;

	public PropertiesDataLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Properties load() {
		try {
			Properties properties = new Properties();
			properties.load(dataSource.getInputStream());
			return properties;
		} catch (IOException e) {
			throw new RuntimeException("Failed to load properties from " + dataSource.getResourceName(), e);
		}
	}

}