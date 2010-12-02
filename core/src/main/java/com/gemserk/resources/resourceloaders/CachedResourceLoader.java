package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.Resource;

public class CachedResourceLoader<T> implements ResourceLoader<T> {

	private final ResourceLoader<T> resourceLoader;

	Resource<T> cachedResource;

	public CachedResourceLoader(ResourceLoader<T> resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public Resource<T> load() {
		if (cachedResource == null)
			cachedResource = resourceLoader.load();
		return cachedResource;
	}

}