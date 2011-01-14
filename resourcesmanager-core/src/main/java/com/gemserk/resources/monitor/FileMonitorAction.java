package com.gemserk.resources.monitor;

import java.io.File;

import com.gemserk.resources.monitor.handlers.FileHandler;

public class FileMonitorAction {

	private final FilesMonitor filesMonitor;
	
	private FileHandler fileHandler;

	public FileMonitorAction(FilesMonitor filesMonitor) {
		this.filesMonitor = filesMonitor;
	}
	
	public FileMonitorAction with(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
		return this;
	}
	
	public void monitor(File file) {
		filesMonitor.register(new FileMonitor(new FileInformationImpl(file), fileHandler));
	}
	
}