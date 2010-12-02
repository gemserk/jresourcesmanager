package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings("unchecked")
public interface ResourceManagerOld {

	void registerDataLoader(Object id, DataLoader dataLoader);

	void registerDataLoader(Object id, DataLoader dataLoader, boolean cached);

	<T, K extends T> Resource<K> get(Object id);

	void reload(Object id);

	void reloadAll();

	<T, K extends T> Resource<K> get(Object id, boolean deferred);

}