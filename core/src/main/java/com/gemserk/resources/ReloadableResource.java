package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

public class ReloadableResource<T> extends Resource<T> {

	DataLoader<T> dataLoader;

	public ReloadableResource(DataLoader<T> dataLoader) {
		this(dataLoader, false);
	}

	public ReloadableResource(DataLoader<T> dataLoader, boolean deferred) {
		super(null);
		this.dataLoader = dataLoader;
		if (!deferred)
			reload();
	}

	@Override
	public T get() {
		T currentData = super.get();
		if (currentData == null) {
			currentData = dataLoader.load();
			super.set(currentData);
		}
		return currentData;
	}

	public void reload() {
		T currentData = super.get();
		if (currentData != null)
			dataLoader.dispose(currentData);
		set(dataLoader.load());
	}

}