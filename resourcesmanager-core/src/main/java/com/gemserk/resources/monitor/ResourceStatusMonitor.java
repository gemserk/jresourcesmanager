package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;

public class ResourceStatusMonitor {
	
	Resource<?> monitoredResource;
	boolean loaded;
	
	public void setMonitoredResource(Resource<?> monitoredResource) {
		this.monitoredResource = monitoredResource;
		loaded = monitoredResource.isLoaded();
	}

	public ResourceStatusMonitor(Resource<?> monitoredResource) {
		setMonitoredResource(monitoredResource);
	}
	
	public boolean wasLoaded() {
		return loaded;
	}
	
	public void checkChanges() {
		loaded = !loaded && monitoredResource.isLoaded();
	}

}
