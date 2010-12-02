package com.gemserk.resources.dataloaders;

public abstract class DataLoader<T> {

	public abstract T load();
	
	/**
	 * To implement custom dispose logic, called when reloading an item from resource manager.
	 */
	public void dispose(T t) {
		
	}

}