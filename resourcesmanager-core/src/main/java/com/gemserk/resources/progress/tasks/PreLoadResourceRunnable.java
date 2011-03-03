package com.gemserk.resources.progress.tasks;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;

/**
 * @author arielsan
 * Asks to the resource manager to load a resource.
 */
public class PreLoadResourceRunnable<K> implements Runnable {

	K resourceId;

	ResourceManager<K> resourceManager;

	public K getResourceId() {
		return resourceId;
	}

	public PreLoadResourceRunnable(ResourceManager<K> resourceManager, K resourceId) {
		this.resourceId = resourceId;
		this.resourceManager = resourceManager;
	}

	@Override
	public void run() {
		Resource<Object> resource = resourceManager.get(resourceId);
		resource.get();
	}

}