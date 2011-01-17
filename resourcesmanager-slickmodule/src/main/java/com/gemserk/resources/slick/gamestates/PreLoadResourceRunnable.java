package com.gemserk.resources.slick.gamestates;

import com.gemserk.resources.ResourceManager;

public class PreLoadResourceRunnable implements Runnable {

	String resourceId;

	ResourceManager resourceManager;

	PreLoadResourceRunnable(ResourceManager resourceManager, String resourceId) {
		this.resourceId = resourceId;
		this.resourceManager = resourceManager;
	}

	@Override
	public void run() {
		resourceManager.get(resourceId).get();
	}
}