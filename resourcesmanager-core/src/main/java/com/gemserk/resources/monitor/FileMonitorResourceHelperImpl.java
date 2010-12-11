package com.gemserk.resources.monitor;

import java.io.File;

import com.gemserk.resources.Resource;
import com.gemserk.resources.monitor.handlers.ReloadResourceWhenFileModified;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class FileMonitorResourceHelperImpl implements FileMonitorResourceHelper {

	FilesMonitor filesMonitor;

	@Inject
	public void setFilesMonitor(FilesMonitor filesMonitor) {
		this.filesMonitor = filesMonitor;
	}

	public void monitorClassPathFile(String file, Resource resource) {
		filesMonitor.monitor(FileUtils.classPathFile(file), new ReloadResourceWhenFileModified(resource));
	}

	public void monitorFileSystemFile(String file, Resource resource) {
		filesMonitor.monitor(new File(file), new ReloadResourceWhenFileModified(resource));
	}

}