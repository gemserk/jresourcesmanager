package com.gemserk.resources;

import com.gemserk.resources.resourceloaders.ResourceLoader;

public interface ResourceManager<K> {

	@SuppressWarnings("rawtypes")
	void add(K id, ResourceLoader resourceLoader);

	<T> Resource<T> get(K id);
	
	void unloadAll();

}