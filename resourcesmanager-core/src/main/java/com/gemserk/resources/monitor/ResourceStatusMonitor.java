package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;

/**
 * Provides a way to monitor the Resource status changes, for example, to know when a Resource was loaded or unloaded.
 * 
 * @author acoppes
 */
public class ResourceStatusMonitor {

	Resource<?> monitoredResource;
	boolean loaded;

	boolean wasLoaded;
	boolean wasUnloaded;

	public void setMonitoredResource(Resource<?> monitoredResource) {
		this.monitoredResource = monitoredResource;
		loaded = monitoredResource.isLoaded();
		wasLoaded = false;
		wasUnloaded = false;
	}

	public ResourceStatusMonitor(Resource<?> monitoredResource) {
		setMonitoredResource(monitoredResource);
	}

	/**
	 * Returns true whenever the Resource was loaded.
	 */
	public boolean wasLoaded() {
		return wasLoaded;
	}

	/**
	 * Returns true whenever the Resource was loaded.
	 */
	public boolean wasUnloaded() {
		return wasUnloaded;
	}

	/**
	 * Call to update the monitor by checking the Resource latest status and to know if it was loaded or unloaded.
	 */
	public void checkChanges() {
		wasLoaded = !loaded && monitoredResource.isLoaded();
		wasUnloaded = loaded && !monitoredResource.isLoaded();
		loaded = monitoredResource.isLoaded();
	}

}
