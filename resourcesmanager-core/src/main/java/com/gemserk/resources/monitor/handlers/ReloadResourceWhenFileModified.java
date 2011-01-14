package com.gemserk.resources.monitor.handlers;

import java.io.File;

import com.gemserk.resources.Resource;

@SuppressWarnings("unchecked")
public class ReloadResourceWhenFileModified extends FileHandler {
	
	private final Resource resource;

	public ReloadResourceWhenFileModified(Resource resource) {
		this.resource = resource;
	}

	@Override
	public void onFileModified(File file) {
		resource.reload();
	}
	
}