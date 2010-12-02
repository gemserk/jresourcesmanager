package com.gemserk.resources;

import com.gemserk.resources.resourceloaders.ResourceLoader;

@SuppressWarnings("unchecked")
public interface ResourceManager<K> {

	void add(K id, ResourceLoader resourceLoader);

	<T> Resource<T> get(K id);

}