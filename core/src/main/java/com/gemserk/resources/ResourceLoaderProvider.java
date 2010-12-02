package com.gemserk.resources;


public class ResourceLoaderProvider<K, T> {

	public ResourceLoader<T> get(K id) {
		throw new RuntimeException("Resource loader for " + id + " not found");
	}

}