package com.gemserk.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gemserk.resources.resourceloaders.ResourceLoader;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResourceManagerImpl<K> implements ResourceManager<K> {

	Map<K, ResourceLoader> resourceLoaders = new HashMap<K, ResourceLoader>();

	ArrayList<Resource> resources = new ArrayList<Resource>();

	public <T> Resource<T> get(K id) {
		if (!resourceLoaders.containsKey(id))
			return null;
		return getResource(id);
	}

	@Override
	public <T> T getResourceValue(K id) {
		Resource<T> resource = get(id);
		return resource.get();
	}

	@Override
	public void unloadAll() {
		for (int i = 0; i < resources.size(); i++)
			resources.get(i).unload();
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