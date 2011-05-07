package com.gemserk.resources.monitor;

import java.io.File;

import com.gemserk.resources.Resource;
import com.gemserk.resources.monitor.handlers.ReloadResourceWhenFileModified;

public class FileMonitorResourceHelperImpl implements FileMonitorResourceHelper {

	private FilesMonitor filesMonitor;

	public void setFilesMonitor(FilesMonitor filesMonitor) {
		this.filesMonitor = filesMonitor;
	}

	@SuppressWarnings("rawtypes")
	public void monitorClassPathFile(String file, Resource resource) {
		new FileMonitorAction(filesMonitor).with(new ReloadResourceWhenFileModified(resource)).monitor(FileUtils.classPathFile(file));
	}

	@SuppressWarnings("rawtypes")
	public void monitorFileSystemFile(String file, Resource resource) {
		new FileMonitorAction(filesMonitor).with(new ReloadResourceWhenFileModified(resource)).monitor(new File(file));
	}

}