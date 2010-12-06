package com.gemserk.resources;

import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.datasources.DataSourceProvider.Source;
import com.gemserk.resources.java2d.Java2dResourcesBuilder;
import com.gemserk.resources.slick.SlickResourcesBuilder;

/**
 * Provides an easier way to add resources to ResourceManager for java2d application.
 */
@SuppressWarnings("unchecked")
public class ResourcesBuilder {

	protected SlickResourcesBuilder slick;
	
	protected Java2dResourcesBuilder java2d;
	
	public ResourcesBuilder(ResourceManager resourceManager) {
		this(resourceManager, new DataSourceProvider(Source.ClassPath));
	}
	
	public ResourcesBuilder(ResourceManager resourceManager, DataSourceProvider dataSourceProvider) {
		this.slick = new SlickResourcesBuilder(resourceManager);
		this.java2d = new Java2dResourcesBuilder(resourceManager, dataSourceProvider);
	}

}