package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

/**
 * Used only as an internal class to know whether a resource is normal or volatile.
 * 
 * @author acoppes
 * 
 * @param <T>
 */
class VolatileResource<T> extends Resource<T> {

	VolatileResource(DataLoader<T> dataLoader) {
		super(dataLoader);
	}

	VolatileResource(DataLoader<T> dataLoader, boolean deferred) {
		super(dataLoader, deferred);
	}
}
