package com.gemserk.resources.monitor;

import java.io.File;

import com.gemserk.resources.monitor.handlers.FileStatusChangedHandler;

public class FileMonitorAction {

	private final FilesMonitor filesMonitor;
	
	private FileStatusChangedHandler fileStatusChangedHandler;

	public FileMonitorAction(FilesMonitor filesMonitor) {
		this.filesMonitor = filesMonitor;
	}
	
	public FileMonitorAction with(FileStatusChangedHandler fileStatusChangedHandler) {
		this.fileStatusChangedHandler = fileStatusChangedHandler;
		return this;
	}
	
	public void monitor(File file) {
		filesMonitor.register(new FileMonitor(new FileInformationImpl(file), fileStatusChangedHandler));
	}
	
}