package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

public class ResourceManagerChildImpl<K> implements ResourceManager<K> {

	ResourceManager<K> parentResourceManager;
	ResourceManager<K> resourceManager;

	public ResourceManagerChildImpl(ResourceManager<K> parentResourceManager) {
		this.parentResourceManager = parentResourceManager;
		this.resourceManager = new ResourceManagerImpl<K>();
	}

	public <T> Resource<T> get(K id) {
		Resource resource = resourceManager.get(id);
		if (resource == null)
			return parentResourceManager.get(id);
		return resource;
	}

	@Override
	public <T> T getResourceValue(K id) {
		T resourceValue = resourceManager.getResourceValue(id);
		if (resourceValue == null)
			return parentResourceManager.getResourceValue(id);
		return resourceValue;
	}

	@Override
	public void unloadAll() {
		resourceManager.unloadAll();
	}

	public void add(K id, DataLoader dataLoader) {
		resourceManager.add(id, dataLoader);
	}

	@Override
	public void addVolatile(K id, DataLoader dataLoader) {
		resourceManager.addVolatile(id, dataLoader);
	}

	@Override
	public int getResourcesCount() {
		return resourceManager.getResourcesCount() + parentResourceManager.getResourcesCount();
	}

	@Override
	public <T> Resource<T> getResourceFromIndex(int index) {
		if (index < resourceManager.getResourcesCount())
			return resourceManager.getResourceFromIndex(index);
		return parentResourceManager.getResourceFromIndex(index - resourceManager.getResourcesCount());
	}

	@Override
	public K getKeyFromIndex(int index) {
		if (index < resourceManager.getResourcesCount())
			return resourceManager.getKeyFromIndex(index);
		return parentResourceManager.getKeyFromIndex(index - resourceManager.getResourcesCount());
	}

}