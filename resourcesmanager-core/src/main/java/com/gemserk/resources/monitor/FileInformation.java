package com.gemserk.resources.monitor;

import java.io.File;

public interface FileInformation {

	boolean wasModified();

	/**
	 * updates to the last file date.
	 */
	void update();
	
	File getFile();

}