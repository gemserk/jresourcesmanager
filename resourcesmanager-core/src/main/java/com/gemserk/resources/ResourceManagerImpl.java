package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResourceManagerImpl<K> implements ResourceManager<K> {

	Map<K, Resource> resources = new HashMap<K, Resource>();

	public <T> Resource<T> get(K id) {
		if (!resources.containsKey(id))
			return null;
		Resource resource = resources.get(id);
		
		// returns a new resource not tracked by the resource manager.
		if (resource instanceof VolatileResource)
			return new VolatileResource(resource);
		
		return resource;
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
		Set<K> keySet = resources.keySet();
		for (K k : keySet) 
			resources.get(k).unload();
	}

	public void add(K id, DataLoader dataLoader) {
		resources.put(id, new Resource(dataLoader, true));
	}

	@Override
	public void addVolatile(K id, DataLoader dataLoader) {
		resources.put(id, new VolatileResource(dataLoader, true));
	}

}