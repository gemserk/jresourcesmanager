package com.gemserk.resources.slick;

import java.util.Properties;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.PropertiesDataLoader;
import com.gemserk.resources.datasources.DataSourceParser;
import com.gemserk.resources.slick.dataloaders.ClonedSlickAnimationLoader;
import com.gemserk.resources.slick.dataloaders.SlickAnimationLoader;

@SuppressWarnings("unchecked")
public class PropertiesAnimationLoader extends PropertiesBaseLoader {

	DataSourceParser dataSourceParser = new DataSourceParser();

	@SuppressWarnings("rawtypes")
	public void load(String propertiesFile) {

		PropertiesDataLoader propertiesLoader = new PropertiesDataLoader(dataSourceParser.parse(propertiesFile));
		Properties properties = propertiesLoader.load();

		for (Object keyObj : properties.keySet()) {
			String id = (String) keyObj;
			String value = properties.getProperty(id);

			String[] values = value.split(",");
			String file = values[0];
			int width = Integer.parseInt(values[1]);
			int height = Integer.parseInt(values[2]);
			final int time = Integer.parseInt(values[3]);
			final int framesCount = Integer.parseInt(values[4]);

			DataLoader dataLoader = new ClonedSlickAnimationLoader(new SlickAnimationLoader(dataSourceParser.parse(file), width, height, time, framesCount, false));
			resourceManager.add(id, dataLoader);
		}

	}

}