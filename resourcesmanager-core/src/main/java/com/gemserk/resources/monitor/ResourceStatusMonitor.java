package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;

/**
 * Provides a way to monitor the status changes (load and unload) of a Resource to possible take actions, for example, call a ResourceStatusChangedHandler.
 * 
 * @author acoppes
 */
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

	/**
	 * Returns true whenever the Resource was loaded.
	 */
	public boolean wasLoaded() {
		return loaded;
	}

	/**
	 * Returns true whenever the Resource was loaded.
	 */
	public boolean wasUnloaded() {
		return unloaded;
	}

	/**
	 * Call to update the monitor by checking the Resource latest status and to know if it was loaded or unloaded.
	 */
	public void checkChanges() {
		loaded = !loaded && monitoredResource.isLoaded();
		unloaded = !unloaded && !monitoredResource.isLoaded();
	}

}
