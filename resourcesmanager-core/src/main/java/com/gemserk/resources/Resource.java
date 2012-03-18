package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

/**
 * 
 * This class provides an indirection to data to be used. It lets you know state of that data like if it is loaded or not, and probably more in the future.
 * 
 * @author acoppes
 * 
 */
public class Resource<T> {

	T data = null;

	DataLoader<T> dataLoader;

	protected Resource(DataLoader<T> dataLoader) {
		this(dataLoader, true);
	}

	protected Resource(DataLoader<T> dataLoader, boolean deferred) {
		this.dataLoader = dataLoader;
		if (!deferred)
			reload();
	}

	/**
	 * Returns the data stored by the Resource.
	 */
	public T get() {
		load();
		return data;
	}

	public void set(T data) {
		this.data = data;
	}

	public void setDataLoader(DataLoader<T> dataLoader) {
		if (isLoaded())
			this.dataLoader.unload(data);
		this.dataLoader = dataLoader;
		this.data = null;
	}

	/**
	 * Reloads the internal data by calling unload and load().
	 */
	public void reload() {
		unload();
		load();
	}

	/**
	 * Loads the data if it wasn't loaded yet, it does nothing otherwise.
	 */
	public void load() {
		if (!isLoaded())
			data = dataLoader.load();
	}

	/**
	 * Unloads the data by calling the DataLoader.unload(t) method.
	 */
	public void unload() {
		if (isLoaded()) {
			dataLoader.unload(data);
			data = null;
		}
	}

	/**
	 * Returns true if the data is loaded, false otherwise.
	 */
	public boolean isLoaded() {
		return data != null;
	}

	@Override
	public Resource<T> clone() {
		return new Resource<T>(dataLoader);
	}

}