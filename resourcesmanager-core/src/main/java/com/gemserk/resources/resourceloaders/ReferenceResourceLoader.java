package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.Resource;

public class ReferenceResourceLoader<T> implements ResourceLoader<T> {
	
	private final ResourceLoader<T> resourceLoader;

	public ReferenceResourceLoader(ResourceLoader<T> resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public Resource<T> load() {
		return resourceLoader.load();
	}

}
