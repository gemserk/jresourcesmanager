package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

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

	public void reload() {
		unload();
		load();
	}

	public void load() {
		if (!isLoaded())
			data = dataLoader.load();
	}

	public void unload() {
		if (isLoaded()) {
			dataLoader.unload(data);
			data = null;
		}
	}

	public boolean isLoaded() {
		return data != null;
	}
	
	@Override
	public Resource clone() {
		return new Resource(dataLoader);
	}

}