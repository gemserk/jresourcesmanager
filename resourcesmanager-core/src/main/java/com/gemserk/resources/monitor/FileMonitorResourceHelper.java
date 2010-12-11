package com.gemserk.resources.monitor;

import com.gemserk.resources.Resource;

@SuppressWarnings("unchecked")
public interface FileMonitorResourceHelper {

	void monitorClassPathFile(String file, Resource resource);

	void monitorFileSystemFile(String file, Resource resource);

}