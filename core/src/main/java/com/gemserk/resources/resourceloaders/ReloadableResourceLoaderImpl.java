package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.ReloadableResource;
import com.gemserk.resources.dataloaders.DataLoader;

public class ReloadableResourceLoaderImpl<T> implements ReloadableResourceLoader<T> {

	final DataLoader<T> dataLoader;

	final boolean deferred;

	public ReloadableResourceLoaderImpl(DataLoader<T> dataLoader) {
		this(dataLoader, false);
	}

	public ReloadableResourceLoaderImpl(DataLoader<T> dataLoader, boolean deferred) {
		this.dataLoader = dataLoader;
		this.deferred = deferred;
	}

	@Override
	public ReloadableResource<T> load() {
		return new ReloadableResource<T>(dataLoader, deferred);
	}

}