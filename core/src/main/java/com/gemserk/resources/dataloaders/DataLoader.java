package com.gemserk.resources.dataloaders;

public abstract class DataLoader<T> {

	protected boolean cached;
	
	public DataLoader() {
		this(true);
	}

	public DataLoader(boolean cached) {
		this.cached = cached;
	}

	public abstract T load();
	
	public boolean isCached() {
		return cached;
	}
	
	public void setCached(boolean cached) {
		this.cached = cached;
	}
	
	/**
	 * To implement custom dispose logic, called when reloading an item from resource manager.
	 */
	public void dispose(T t) {
		
	}

}