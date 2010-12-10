package com.gemserk.resources.monitor;

public interface ResourcesMonitor {

	void reloadModifiedResources();

	void monitor(ResourceMonitor resourceMonitor);

}