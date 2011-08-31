package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

/**
 * Provides an implementation of Resource returning new data each time get() is called instead caching it like Resource does.
 * 
 * @author acoppes
 * 
 */
public class VolatileResource<T> extends Resource<T> {

	public VolatileResource(DataLoader<T> dataLoader) {
		super(dataLoader, true);
	}
	
	@Override
	public T get() {
		return dataLoader.load();
	}
	
	@Override
	public void load() {
		
	}
	
	@Override
	public void unload() {
		
	}
	
	@Override
	public boolean isLoaded() {
		return true;
	}

}
