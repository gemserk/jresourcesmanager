package com.gemserk.resources;

public abstract class ResourceLoader<T> {

	protected boolean cached;
	
	public ResourceLoader() {
		this(true);
	}

	public ResourceLoader(boolean cached) {
		this.cached = cached;
	}

	public abstract T load();
	
	public boolean isCached() {
		return cached;
	}
	
	void setCached(boolean cached) {
		this.cached = cached;
	}
	
	/**
	 * To implement custom dispose logic, called when reloading an item from resource manager.
	 */
	public void dispose(T t) {
		
	}

}