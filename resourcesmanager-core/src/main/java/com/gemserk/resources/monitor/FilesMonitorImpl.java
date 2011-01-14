package com.gemserk.resources.monitor;

import java.io.File;
import java.util.ArrayList;

import com.gemserk.resources.monitor.handlers.FileHandler;

public class FilesMonitorImpl implements FilesMonitor {
	
	private ArrayList<FileMonitor> fileMonitors = new ArrayList<FileMonitor>();

	public void checkModifiedFiles() {
		ArrayList<FileMonitor> tmpArraylist = new ArrayList<FileMonitor>(fileMonitors);
		for (FileMonitor fileMonitor : tmpArraylist)
			fileMonitor.callHandlerIfModified();
	}

	public void monitor(File file, FileHandler fileHandler) {
		register(new FileMonitor(new FileInformationImpl(file), fileHandler));
	}

	@Override
	public void register(FileMonitor fileMonitor) {
		fileMonitors.add(fileMonitor);
	}
	
}