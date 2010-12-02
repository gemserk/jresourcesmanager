package com.gemserk.resources.resourceloaders;

import java.io.InputStream;

import com.gemserk.resources.ResourceLoader;

public abstract class ClassPathResourceLoader<T> extends ResourceLoader<T> {

	protected final String url;

	public ClassPathResourceLoader(String url) {
		this.url = url;
	}

	@Override
	public T load() {
		InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(url);
		if (resourceStream == null)
			throw new RuntimeException("Failed to get resource stream for " + url);
		return load(resourceStream);
	}

	protected abstract T load(InputStream resourceStream);

}