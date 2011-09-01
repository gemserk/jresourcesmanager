package com.gemserk.resources.slick.gamestates;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.progress.TaskQueue;
import com.gemserk.resources.progress.tasks.PreLoadResourceRunnable;

public class ResourceManagerLoaderProxyImpl<K> implements ResourceManager<K> {

	private ResourceManager<K> resourceManager;

	private TaskQueue taskQueue;
	
	public void setTaskQueue(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void setResourceManager(ResourceManager<K> resourceManager) {
		this.resourceManager = resourceManager;
	}
	
	public ResourceManagerLoaderProxyImpl() {
		
	}
	
	public ResourceManagerLoaderProxyImpl(ResourceManager<K> resourceManager) {
		this(resourceManager, new TaskQueue());
	}

	public ResourceManagerLoaderProxyImpl(ResourceManager<K> resourceManager, TaskQueue taskQueue) {
		this.resourceManager = resourceManager;
		this.taskQueue = taskQueue;
	}

	@SuppressWarnings("rawtypes")
	public void add(K id, DataLoader dataLoader) {
		resourceManager.add(id, dataLoader);
		taskQueue.add(new PreLoadResourceRunnable<K>(resourceManager, id));
	}

	public <T> Resource<T> get(K id) {
		return resourceManager.get(id);
	}

	@Override
	public void unloadAll() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public <T> T getResourceValue(K id) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public void addVolatile(K id, DataLoader dataLoader) {
		// TODO Auto-generated function stub
		
	}

}