package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;

@SuppressWarnings("unchecked")
public class ResourceMonitor {

	private final FileMonitor fileMonitor;

	private final Resource resource;

	public ResourceMonitor(Resource resource, FileMonitor fileMonitor) {
		this.resource = resource;
		this.fileMonitor = fileMonitor;
	}

	public boolean reloadIfModified() {
		boolean modified = fileMonitor.wasModified();
		if (modified) {
			resource.reload();
			fileMonitor.reset();
		}
		return modified;
	}

}