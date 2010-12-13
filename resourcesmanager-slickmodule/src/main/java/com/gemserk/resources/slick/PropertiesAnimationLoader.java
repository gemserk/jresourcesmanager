package com.gemserk.resources.slick;

import java.util.Properties;

import com.gemserk.resources.PropertiesLoader;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSourceParser;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickAnimationLoader;

@SuppressWarnings("unchecked")
public class PropertiesAnimationLoader extends PropertiesBaseLoader {

	PropertiesLoader propertiesLoader = new PropertiesLoader();

	DataSourceParser dataSourceParser = new DataSourceParser();

	public void load(String propertiesFile) {

		Properties properties = propertiesLoader.load(dataSourceParser.parse(propertiesFile));

		for (Object keyObj : properties.keySet()) {
			String id = (String) keyObj;
			String value = properties.getProperty(id);

			String[] values = value.split(",");
			String file = values[0];
			int width = Integer.parseInt(values[1]);
			int height = Integer.parseInt(values[2]);
			final int time = Integer.parseInt(values[3]);
			final int framesCount = Integer.parseInt(values[4]);

			DataLoader dataLoader = new SlickAnimationLoader(dataSourceParser.parse(file), width, height, time, framesCount, false);
			resourceManager.add(id, new ResourceLoaderImpl(dataLoader));

			// // mark the resource for reloading whenever the properties file was modified
			// resourcesMonitor.monitor(new ResourceMonitor(resourceManager.get(id), FileMonitorFactory.classPathFileMonitor(propertiesFile)));

			// or the resource file itself
			// resourcesMonitor.monitor(new ResourceMonitor(resourceManager.get(id), FileMonitorFactory.classPathFileMonitor(file)));
			fileMonitorResourceHelper.monitorClassPathFile(file, resourceManager.get(id));
		}

	}

}