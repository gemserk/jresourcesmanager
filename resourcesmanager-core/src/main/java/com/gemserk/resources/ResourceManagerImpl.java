package com.gemserk.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResourceManagerImpl<K> implements ResourceManager<K> {

	Map<K, Resource> resources = new HashMap<K, Resource>();
	ArrayList<K> resourcesList = new ArrayList<K>();

	public <T> Resource<T> get(K id) {
		if (!resources.containsKey(id))
			return null;
		Resource resource = resources.get(id);
		
		// returns a new resource not tracked by the resource manager.
		if (resource instanceof VolatileResource)
			return resource.clone();
		
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
		if (!resourcesList.contains(id))
			resourcesList.add(id);
	}

	@Override
	public void addVolatile(K id, DataLoader dataLoader) {
		resources.put(id, new VolatileResource(dataLoader, true));
		if (!resourcesList.contains(id))
			resourcesList.add(id);
	}

	@Override
	public int getResourcesCount() {
		return resourcesList.size();
	}

	@Override
	public <T> Resource<T> getResourceFromIndex(int index) {
		return resources.get(resourcesList.get(index));
	}

	@Override
	public K getKeyFromIndex(int index) {
		return resourcesList.get(index);
	}

}