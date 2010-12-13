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

	PropertiesLoader propertiesLoader = new PropertiesLoader();

	public void load(String propertiesFile) {

		Properties properties = propertiesLoader.load(new ClassPathDataSource(propertiesFile));

		for (String id : properties.stringPropertyNames()) {
			String imageProperties = properties.getProperty(id);
			String[] values = imageProperties.split(",");

			String file = values[0];

			DataLoader dataLoader = new SlickSoundLoader(file);
			resourceManager.add(id, new CachedResourceLoader(new ResourceLoaderImpl(dataLoader)));

			// // mark the resource for reloading whenever the properties file was modified
			// resourcesMonitor.monitor(new ResourceMonitor(resourceManager.get(id), FileMonitorFactory.classPathFileMonitor(propertiesFile)));

			// or the resource file itself
			// resourcesMonitor.monitor(new ResourceMonitor(resourceManager.get(id), FileMonitorFactory.classPathFileMonitor(file)));
			fileMonitorResourceHelper.monitorClassPathFile(file, resourceManager.get(id));
		}
	}
}