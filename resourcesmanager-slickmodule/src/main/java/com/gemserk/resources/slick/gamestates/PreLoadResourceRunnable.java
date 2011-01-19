package com.gemserk.resources.slick.gamestates;

import com.gemserk.resources.ResourceManager;

/**
 * @author arielsan
 * Asks to the resource manager to load a resource.
 */
public class PreLoadResourceRunnable<K> implements Runnable {

	K resourceId;

	ResourceManager<K> resourceManager;

	public PreLoadResourceRunnable(ResourceManager<K> resourceManager, K resourceId) {
		this.resourceId = resourceId;
		this.resourceManager = resourceManager;
	}

	@Override
	public void run() {
		resourceManager.get(resourceId).get();
	}
}