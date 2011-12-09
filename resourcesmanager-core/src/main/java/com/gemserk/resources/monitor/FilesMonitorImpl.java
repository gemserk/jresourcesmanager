package com.gemserk.resources.monitor;

import java.io.File;
import java.util.ArrayList;

import com.gemserk.resources.Resource;
import com.gemserk.resources.datasources.DataSource;
import com.gemserk.resources.monitor.handlers.FileStatusChangedHandler;
import com.gemserk.resources.monitor.handlers.ReloadResourceWhenFileModified;

public class FilesMonitorImpl implements FilesMonitor {
	
	private ArrayList<FileMonitor> fileMonitors = new ArrayList<FileMonitor>();

	public void checkModifiedFiles() {
		ArrayList<FileMonitor> tmpArraylist = new ArrayList<FileMonitor>(fileMonitors);
		for (FileMonitor fileMonitor : tmpArraylist)
			fileMonitor.callHandlerIfModified();
	}

	public void monitor(File file, FileStatusChangedHandler fileStatusChangedHandler) {
		register(new FileMonitor(new FileInformationImpl(file), fileStatusChangedHandler));
	}

	@Override
	public void register(FileMonitor fileMonitor) {
		fileMonitors.add(fileMonitor);
	}

	@Override
	public void monitor(DataSource dataSource, Resource<?> resource) {
		new FileMonitorAction(this).with(new ReloadResourceWhenFileModified(resource)).monitor(new File(dataSource.getUri()));
	}
	
}