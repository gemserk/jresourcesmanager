package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

public class ResourceLoaderProvider<K, T> {

	Map<K, ResourceLoader<T>> resourceLoaders = new HashMap<K, ResourceLoader<T>>();

	public ResourceLoader<T> get(K id) {
		if (resourceLoaders.containsKey(id))
			return resourceLoaders.get(id);
		return getDefaultLoader(id);
	}

	public void registerResourceLoader(K id, ResourceLoader<T> resourceLoader) {
		registerResourceLoader(id, resourceLoader, true);
	}
	
	public void registerResourceLoader(K id, ResourceLoader<T> resourceLoader, boolean cached) {
		resourceLoaders.put(id, resourceLoader);
		resourceLoader.setCached(cached);
	}
	
	protected ResourceLoader<T> getDefaultLoader(K id) {
		throw new RuntimeException("Resource loader for " + id + " not found");
	}

}