package com.gemserk.resources;

import com.gemserk.resources.resourceloaders.ReloadableResourceLoader;

@SuppressWarnings("unchecked")
public interface ResourceManager<K> {

	void add(K id, ReloadableResourceLoader reloadableResourceLoader);

	<T> ReloadableResource<T> get(K id);

}