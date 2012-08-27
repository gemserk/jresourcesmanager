package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

public class ResourceManagerChildImpl<K> implements ResourceManager<K> {

	ResourceManager<K> parentResourceManager;
	ResourceManager<K> childResourceManager;

	public ResourceManagerChildImpl(ResourceManager<K> parentResourceManager) {
		this.parentResourceManager = parentResourceManager;
		this.childResourceManager = new ResourceManagerImpl<K>();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> Resource<T> get(K id) {
		Resource resource = childResourceManager.get(id);
		if (resource == null)
			return parentResourceManager.get(id);
		return resource;
	}

	@Override
	public <T> T getResourceValue(K id) {
		T resourceValue = childResourceManager.getResourceValue(id);
		if (resourceValue == null)
			return parentResourceManager.getResourceValue(id);
		return resourceValue;
	}

	@Override
	public void unloadAll() {
		childResourceManager.unloadAll();
	}

	public void add(K id, @SuppressWarnings("rawtypes") DataLoader dataLoader) {
		childResourceManager.add(id, dataLoader);
	}

	@Override
	public void addVolatile(K id, @SuppressWarnings("rawtypes") DataLoader dataLoader) {
		childResourceManager.addVolatile(id, dataLoader);
	}

	@Override
	public int getResourcesCount() {
		return childResourceManager.getResourcesCount() + parentResourceManager.getResourcesCount();
	}

	@Override
	public <T> Resource<T> getResourceFromIndex(int index) {
		if (index < childResourceManager.getResourcesCount())
			return childResourceManager.getResourceFromIndex(index);
		return parentResourceManager.getResourceFromIndex(index - childResourceManager.getResourcesCount());
	}

	@Override
	public K getKeyFromIndex(int index) {
		if (index < childResourceManager.getResourcesCount())
			return childResourceManager.getKeyFromIndex(index);
		return parentResourceManager.getKeyFromIndex(index - childResourceManager.getResourcesCount());
	}

}