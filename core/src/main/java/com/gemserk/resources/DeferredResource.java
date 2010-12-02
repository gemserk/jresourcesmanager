package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

public class DeferredResource<T> extends Resource<T> {

	private final DataLoader<T> dataLoader;

	public DeferredResource(DataLoader<T> dataLoader) {
		super(null);
		this.dataLoader = dataLoader;
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
	
}