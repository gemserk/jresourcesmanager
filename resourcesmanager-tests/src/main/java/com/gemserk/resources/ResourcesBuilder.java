package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.datasources.DataSourceProvider.Source;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;
import com.gemserk.resources.java2d.dataloaders.TrueTypeFontLoader;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.google.inject.Inject;

/**
 * Provides an easier way to add resources to ResourceManager for java2d application.
 */
@SuppressWarnings("unchecked")
public class ResourcesBuilder {

	private ResourceManager resourceManager;

	private DataSourceProvider dataSourceProvider;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Inject
	public void setDataSourceProvider(DataSourceProvider dataSourceProvider) {
		this.dataSourceProvider = dataSourceProvider;
	}
	
	public ResourcesBuilder() {
		this(null);
	}
	
	public ResourcesBuilder(ResourceManager resourceManager) {
		this(resourceManager, new DataSourceProvider(Source.ClassPath));
	}
	
	public ResourcesBuilder(ResourceManager resourceManager, DataSourceProvider dataSourceProvider) {
		this.resourceManager = resourceManager;
		this.dataSourceProvider = dataSourceProvider;
	}

	public void image(String id, String file) {
		DataLoader dataLoader = new ImageLoader(dataSourceProvider.get(file));
		addCachedResourceLoader(id, dataLoader);
	}

	public void font(String id, String file, int style, int size) {
		DataLoader dataLoader = new TrueTypeFontLoader(dataSourceProvider.get(file), style, size);
		addCachedResourceLoader(id, dataLoader);
	}

	private void addCachedResourceLoader(String id, DataLoader dataLoader) {
		resourceManager.add(id, new CachedResourceLoader(new ResourceLoaderImpl(dataLoader)));
	}
}