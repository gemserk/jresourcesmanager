package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

import com.gemserk.resources.resourceloaders.ResourceLoader;

@SuppressWarnings("rawtypes")
public class ResourcesMonitorImpl<K> implements ResourcesMonitor, ResourceManager<K> {
	
	ResourceManager<K> resourceManager;
	
	Map<K, Resource> monitoredResources = new HashMap<K, Resource>();
	
	public ResourcesMonitorImpl(ResourceManager<K> resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Override
	public void add(K id, ResourceLoader resourceLoader) {
		resourceManager.add(id, resourceLoader);
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
		resourceManager.unloadAll();
	}
	
}