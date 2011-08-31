package com.gemserk.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResourceManagerImpl<K> implements ResourceManager<K> {

	Map<K, Resource> resourceLoaders = new HashMap<K, Resource>();

	Set<Resource> resources = new HashSet<Resource>();

	public <T> Resource<T> get(K id) {
		if (!resourceLoaders.containsKey(id))
			return null;
		return getResource(id);
	}

	@Override
	public <T> T getResourceValue(K id) {
		Resource<T> resource = get(id);
		if (resource == null)
			return null;
		return resource.get();
	}

	@Override
	public void unloadAll() {
		for (Resource resource : resources)
			resource.unload();
	}

	private Resource getResource(K id) {
		Resource resource = resourceLoaders.get(id);
		resources.add(resource);
		return resource;
	}

	public void add(K id, DataLoader dataLoader) {
		resourceLoaders.put(id, new Resource(dataLoader, true));
	}

}