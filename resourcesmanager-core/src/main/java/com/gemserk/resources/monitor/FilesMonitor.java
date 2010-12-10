package com.gemserk.resources.monitor;

import java.io.File;

import com.gemserk.resources.monitor.handlers.FileModifiedHandler;

public interface FilesMonitor {

	/**
	 * Checks if any of the monitored files has been modified. 
	 */
	void checkModifiedFiles();

	/**
	 * Will monitor changes on specified file and call fileModifiedHandler whenever a modification has been detected.
	 */
	void monitor(File file, FileModifiedHandler fileModifiedHandler);

}