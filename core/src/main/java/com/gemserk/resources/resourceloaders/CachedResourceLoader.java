package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.ReloadableResource;

public class CachedResourceLoader<T> implements ReloadableResourceLoader<T> {

	private final ReloadableResourceLoader<T> reloadableResourceLoader;

	ReloadableResource<T> cachedResource;

	public CachedResourceLoader(ReloadableResourceLoader<T> resourceLoader) {
		this.reloadableResourceLoader = resourceLoader;
	}

	@Override
	public ReloadableResource<T> load() {
		if (cachedResource == null)
			cachedResource = reloadableResourceLoader.load();
		return cachedResource;
	}

}