package com.gemserk.resources.monitor;

import java.io.File;

public interface FileMonitor {

	boolean wasModified();

	void reset();
	
	File getFile();

}