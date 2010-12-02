package com.gemserk.resources.dataloaders;

/**
 * A DataLoader that always returns the same object instance.
 */
public class StaticDataLoader<T> extends DataLoader<T> {

	private final T t;

	public StaticDataLoader(T t) {
		this.t = t;
	}

	@Override
	public T load() {
		return t;
	}

}