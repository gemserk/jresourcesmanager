package com.gemserk.resources.monitor.handlers;

import java.io.File;

/**
 * Provides an API to handle changes when a File was modified.
 */
public interface FileStatusChangedHandler {
	
	void onFileModified(File file);
	
}