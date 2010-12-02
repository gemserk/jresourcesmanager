package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

import com.gemserk.resources.resourceloaders.ReloadableResourceLoader;

@SuppressWarnings("unchecked")
public class ResourceManagerImpl<K> implements ResourceManager<K> {

	Map<K, ReloadableResourceLoader> reloadableResourceLoaders = new HashMap<K, ReloadableResourceLoader>();

	public <T> ReloadableResource<T> get(K id) {
		if (!reloadableResourceLoaders.containsKey(id))
			return null;
		return reloadableResourceLoaders.get(id).load();
	}

	public void add(K id, ReloadableResourceLoader reloadableResourceLoader) {
		reloadableResourceLoaders.put(id, reloadableResourceLoader);
	}

}