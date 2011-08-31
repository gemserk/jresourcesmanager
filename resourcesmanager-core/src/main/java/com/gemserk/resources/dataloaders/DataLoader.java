package com.gemserk.resources.dataloaders;

public abstract class DataLoader<T> {

	public abstract T load();
	
	/**
	 * To implement custom unload logic, for example if using an OpenGL texture, etc.
	 */
	public void unload(T t) {
		
	}

}