package com.gemserk.resources.dataloaders;

/**
 * Provides a way to define how to load data.
 * 
 * @author acoppes
 * 
 */
public abstract class DataLoader<T> {

	/**
	 * Implements how to load the data.
	 */
	public abstract T load();

	/**
	 * Implements how to unload the data, if it is nod automatic and you need to unload stuff by hand.
	 */
	public void unload(T t) {

	}

}