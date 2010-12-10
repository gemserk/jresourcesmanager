package com.gemserk.resources.slick;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;

@SuppressWarnings("unchecked")
public class PropertiesImageLoader extends PropertiesLoader {

	public void load(String propertiesFile) {
		try {
			Properties properties = new Properties();
			InputStream propertiesInputStream = new ClassPathDataSource(propertiesFile).getInputStream();
			properties.load(propertiesInputStream);

			for (String id : properties.stringPropertyNames()) {
				String imageProperties = properties.getProperty(id);
				String[] values = imageProperties.split(",");

				String file = values[0];

				DataLoader dataLoader = new SlickImageLoader(file);
				resourceManager.add(id, new CachedResourceLoader(new ResourceLoaderImpl(dataLoader)));

				// // mark the resource for reloading whenever the properties file was modified
				// resourcesMonitor.monitor(new ResourceMonitor(resourceManager.get(id), FileMonitorFactory.classPathFileMonitor(propertiesFile)));

				// or the resource file itself
				// resourcesMonitor.monitor(new ResourceMonitor(resourceManager.get(id), FileMonitorFactory.classPathFileMonitor(file)));
				fileMonitorResourceHelper.monitorClassPathFile(file, resourceManager.get(id));
			}
		} catch (IOException e) {
			throw new RuntimeException("failed to load image mapping", e);
		}
	}
}