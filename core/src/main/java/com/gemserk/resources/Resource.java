package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

public class Resource<T> {

	T data = null;

	DataLoader<T> dataLoader;

	public Resource(DataLoader<T> dataLoader) {
		this(dataLoader, false);
	}

	public Resource(DataLoader<T> dataLoader, boolean deferred) {
		this.dataLoader = dataLoader;
		if (!deferred)
			reload();
	}

	public T get() {
		if (data == null)
			data = dataLoader.load();
		return data;
	}

	public void set(T data) {
		this.data = data;
	}

	public void reload() {
		if (data != null)
			dataLoader.dispose(data);
		set(dataLoader.load());
	}

}