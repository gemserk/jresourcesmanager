package com.gemserk.resources.tests;

import java.awt.Image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.ResourcesBuilder;
import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.datasources.DataSourceProvider.Source;

public class ResourcesUnloadSample {
	
	protected static final Logger logger = LoggerFactory.getLogger(ResourcesUnloadSample.class);

	public static void main(String[] args) {

		final ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		new ResourcesBuilder(resourceManager, new DataSourceProvider(Source.ClassPath)) {
			{
				java2d.image("BlackCompanyLogo", "logo-gemserk-512x116.png");
			}
		};
		
		Resource<Image> resource = resourceManager.get("BlackCompanyLogo");
		
		if (logger.isInfoEnabled())
			logger.info("Resource loaded: " + resource.isLoaded());
		
		resource.get();

		if (logger.isInfoEnabled())
			logger.info("Resource loaded: " + resource.isLoaded());
		
		if (logger.isInfoEnabled())
			logger.info("Unloding all resources");
		
		resourceManager.unloadAll();
		
		if (logger.isInfoEnabled())
			logger.info("Resource loaded: " + resource.isLoaded());
		

	}
}
