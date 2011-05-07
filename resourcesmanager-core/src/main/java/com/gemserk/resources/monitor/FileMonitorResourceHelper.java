package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;

public interface FileMonitorResourceHelper {

	@SuppressWarnings("rawtypes")
	void monitorClassPathFile(String file, Resource resource);

	@SuppressWarnings("rawtypes")
	void monitorFileSystemFile(String file, Resource resource);

}