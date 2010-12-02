package com.gemserk.resources.dataloaders;

/**
 * @deprecated Not being used right now really.
 */
public class DataLoaderProvider<K, T> {

	public DataLoader<T> get(K id) {
		throw new RuntimeException("Resource loader for " + id + " not found");
	}

}