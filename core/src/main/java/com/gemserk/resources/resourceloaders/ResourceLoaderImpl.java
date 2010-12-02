package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.Resource;
import com.gemserk.resources.dataloaders.DataLoader;

public class ResourceLoaderImpl<T> implements ResourceLoader<T> {

	final DataLoader<T> dataLoader;

	final boolean deferred;

	public ResourceLoaderImpl(DataLoader<T> dataLoader) {
		this(dataLoader, false);
	}

	public ResourceLoaderImpl(DataLoader<T> dataLoader, boolean deferred) {
		this.dataLoader = dataLoader;
		this.deferred = deferred;
	}

	@Override
	public Resource<T> load() {
		return new Resource<T>(dataLoader, deferred);
	}

}