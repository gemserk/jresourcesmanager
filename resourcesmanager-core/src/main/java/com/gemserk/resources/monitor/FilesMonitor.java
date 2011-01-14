package com.gemserk.resources.monitor;


public interface FilesMonitor {

	/**
	 * Checks if any of the monitored files has been modified. 
	 */
	void checkModifiedFiles();

	/**
	 * Register a file monitor.
	 */
	void register(FileMonitor fileMonitor);

}