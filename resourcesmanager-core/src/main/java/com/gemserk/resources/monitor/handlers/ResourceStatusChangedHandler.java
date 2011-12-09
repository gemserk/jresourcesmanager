package com.gemserk.resources.monitor.handlers;

import com.gemserk.resources.Resource;

/**
 * Provides an API to be aware of Resource status changes like the Resource was loaded or unloaded.
 * 
 * @author acoppes
 */
public interface ResourceStatusChangedHandler {

	/**
	 * Called when the monitored Resource was loaded.
	 * 
	 * @param resource
	 *            The monitored Resource.
	 */
	void onLoaded(@SuppressWarnings("rawtypes") Resource resource);

	/**
	 * Called when the monitored Resource was unloaded.
	 * 
	 * @param resource
	 *            The monitored Resource.
	 */
	void onUnloaded(@SuppressWarnings("rawtypes") Resource resource);

}
