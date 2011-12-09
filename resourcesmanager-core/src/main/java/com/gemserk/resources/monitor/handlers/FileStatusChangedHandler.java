package com.gemserk.resources.monitor.handlers;

import java.io.File;

/**
 * Provides an API to handle changes when a File was modified.
 * 
 * @author acoppes
 */
public interface FileStatusChangedHandler {

	/**
	 * Called when the monitored File was modified. Be aware that the File could be in use when this method was called 
	 * and if you try to reload stuff here it could fail, the idea is to enqueue the reload for later.
	 * 
	 * @param file
	 *            The monitored File.
	 */
	void onFileModified(File file);

}