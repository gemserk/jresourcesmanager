package com.gemserk.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gemserk.resources.resourceloaders.ResourceLoader;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResourceManagerImpl<K> implements ResourceManager<K> {

	Map<K, ResourceLoader> resourceLoaders = new HashMap<K, ResourceLoader>();

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
		Resource resource = resourceLoaders.get(id).load();
		resources.add(resource);
		return resource;
	}

	public void add(K id, ResourceLoader resourceLoader) {
		resourceLoaders.put(id, resourceLoader);
	}

}