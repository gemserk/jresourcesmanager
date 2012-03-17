package com.gemserk.resources;

import com.gemserk.resources.dataloaders.StaticDataLoader;

public class ResourceFactory {

	public static <T> Resource<T> resource(T t) {
		return new Resource<T>(new StaticDataLoader<T>(t));
	}
}
