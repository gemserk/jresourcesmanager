package com.gemserk.resources.dataloaders;

/**
 * A DataLoader that returns a cached object instance from another DataLoader. 
 */
public class CachedDataLoader<T> extends DataLoader<T> {

	private final DataLoader<T> dataLoader;

	private T cachedData;

	public CachedDataLoader(DataLoader<T> resourceLoader) {
		this.dataLoader = resourceLoader;
	}

	@Override
	public T load() {
		if (cachedData == null)
			cachedData = dataLoader.load();
		return cachedData;
	}

}