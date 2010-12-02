package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;


public class DataLoaderProvider<K, T> {

	public DataLoader<T> get(K id) {
		throw new RuntimeException("Resource loader for " + id + " not found");
	}

}