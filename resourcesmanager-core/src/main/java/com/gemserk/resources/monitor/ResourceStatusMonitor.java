package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;

public class ResourceStatusMonitor {
	
	Resource<?> monitoredResource;
	boolean loaded;
	boolean unloaded;
	
	public void setMonitoredResource(Resource<?> monitoredResource) {
		this.monitoredResource = monitoredResource;
		loaded = monitoredResource.isLoaded();
		unloaded = !loaded;
	}

	public ResourceStatusMonitor(Resource<?> monitoredResource) {
		setMonitoredResource(monitoredResource);
	}
	
	public boolean wasLoaded() {
		return loaded;
	}
	
	public boolean wasUnloaded() {
		return unloaded;
	}
	
	public void checkChanges() {
		loaded = !loaded && monitoredResource.isLoaded();
		unloaded = !unloaded && !monitoredResource.isLoaded();
	}

}
