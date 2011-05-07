package com.gemserk.resources.java2d;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;
import com.gemserk.resources.java2d.dataloaders.TrueTypeFontLoader;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Java2dResourcesBuilder {
	
	private ResourceManager resourceManager;
	
	private DataSourceProvider dataSourceProvider;
	
	public Java2dResourcesBuilder(ResourceManager resourceManager, DataSourceProvider dataSourceProvider) {
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