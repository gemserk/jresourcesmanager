package com.gemserk.resources.util.progress.task;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.util.progress.Progress;
import com.gemserk.resources.util.progress.ProgressTask;

/**
 * @author arielsan
 * Asks to the resource manager to load a resource.
 */
public class PreLoadResourceRunnable<K> implements Runnable, ProgressTask {

	K resourceId;

	ResourceManager<K> resourceManager;

	private Progress progress;
	
	public K getResourceId() {
		return resourceId;
	}

	public PreLoadResourceRunnable(ResourceManager<K> resourceManager, K resourceId) {
		this.resourceId = resourceId;
		this.resourceManager = resourceManager;
	}

	@Override
	public void run() {
		progress.setMessage("Loading... " + resourceId.toString());
		Resource<Object> resource = resourceManager.get(resourceId);
		resource.get();
	}

	@Override
	public void setProgress(Progress progress) {
		this.progress = progress;
	}
}