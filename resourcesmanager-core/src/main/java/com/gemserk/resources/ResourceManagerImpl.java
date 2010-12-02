package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

import com.gemserk.resources.resourceloaders.ResourceLoader;

@SuppressWarnings("unchecked")
public class ResourceManagerImpl<K> implements ResourceManager<K> {

	Map<K, ResourceLoader> resourceLoaders = new HashMap<K, ResourceLoader>();

	public <T> Resource<T> get(K id) {
		if (!resourceLoaders.containsKey(id))
			return null;
		return resourceLoaders.get(id).load();
	}

	public void add(K id, ResourceLoader resourceLoader) {
		resourceLoaders.put(id, resourceLoader);
	}

}