package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings("rawtypes")
public class ResourcesMonitorImpl<K> implements ResourcesMonitor, ResourceManager<K> {
	
	ResourceManager<K> resourceManager;
	
	Map<K, Resource> monitoredResources = new HashMap<K, Resource>();
	
	public ResourcesMonitorImpl(ResourceManager<K> resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Override
	public void add(K id, DataLoader dataLoader) {
		resourceManager.add(id, dataLoader);
	}

	@Override
	public <T> Resource<T> get(K id) {
		Resource<T> resource = resourceManager.get(id);
		monitoredResources.put(id, resource);
		return resource;
	}

	@Override
	public void reloadAll() {
		for (K k : monitoredResources.keySet()) {
			Resource resource = monitoredResources.get(k);
			resource.reload();
		}
	}

	@Override
	public void unloadAll() {
		// TODO: should do something with monitored resources...
		resourceManager.unloadAll();
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public <T> T getResourceValue(K id) {
		Resource<T> resource = get(id);
		return resource.get();
	}

	@Override
	public void addVolatile(K id, DataLoader dataLoader) {
		// TODO Auto-generated function stub
		
	}

	@Override
	public int getResourcesCount() {
		return resourceManager.getResourcesCount();
	}

	@Override
	public <T> Resource<T> getResourceFromIndex(int index) {
		return resourceManager.getResourceFromIndex(index);
	}

	@Override
	public K getKeyFromIndex(int index) {
		return resourceManager.getKeyFromIndex(index);
	}
	
}