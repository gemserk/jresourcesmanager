package com.gemserk.resources.java2d;

import java.awt.Image;

import com.gemserk.resources.DataLoaderProvider;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.datasources.DataSourceProvider;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;
import com.google.inject.Inject;

public class ImageLoaderProvider extends DataLoaderProvider<String, Image> {
	
	DataSourceProvider dataSourceProvider;
	
	public ImageLoaderProvider() {
		this(new DataSourceProvider());
	}

	@Inject
	public ImageLoaderProvider(DataSourceProvider dataSourceProvider) {
		this.dataSourceProvider = dataSourceProvider;
	}

	@Override
	public DataLoader<Image> get(String id) {
		return new ImageLoader(new ClassPathDataSource(id));
	}

}