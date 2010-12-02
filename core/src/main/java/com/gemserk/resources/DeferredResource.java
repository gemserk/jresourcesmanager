package com.gemserk.resources;

public class DeferredResource<T> extends Resource<T> {

	private final ResourceLoader<T> resourceLoader;

	public DeferredResource(ResourceLoader<T> resourceLoader) {
		super(null);
		this.resourceLoader = resourceLoader;
	}
	
	@Override
	public T get() {
		T currentData = super.get();
		if (currentData == null) {
			currentData = resourceLoader.load();
			super.set(currentData);
		}
		return currentData;
	}
	
}