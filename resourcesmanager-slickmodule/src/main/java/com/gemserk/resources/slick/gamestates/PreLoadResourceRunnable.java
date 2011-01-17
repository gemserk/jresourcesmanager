package com.gemserk.resources.slick.gamestates;

import com.gemserk.resources.ResourceManager;

public class PreLoadResourceRunnable implements Runnable {

	Object resourceId;

	ResourceManager resourceManager;

	public PreLoadResourceRunnable(ResourceManager resourceManager, Object resourceId) {
		this.resourceId = resourceId;
		this.resourceManager = resourceManager;
	}

	@Override
	public void run() {
		resourceManager.get(resourceId).get();
	}
}