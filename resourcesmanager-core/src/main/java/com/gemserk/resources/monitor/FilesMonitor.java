package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;
import com.gemserk.resources.datasources.DataSource;

public interface FilesMonitor {

	/**
	 * Checks if any of the monitored files has been modified.
	 */
	void checkModifiedFiles();

	/**
	 * Register a file monitor.
	 */
	void register(FileMonitor fileMonitor);

	/**
	 * Creates a new monitor which reloads the resource each time the dataSource data was modified.
	 * 
	 * @param dataSource
	 *            The DataSource to check if the data was modified.
	 * @param resource
	 *            The Resource to reload each time the data was modified.
	 */
	void monitor(DataSource dataSource, Resource<?> resource);

}