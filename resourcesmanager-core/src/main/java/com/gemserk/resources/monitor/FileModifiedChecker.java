package com.gemserk.resources.monitor;

import com.gemserk.resources.monitor.handlers.FileModifiedHandler;

public class FileModifiedChecker {

	private final FileMonitor fileMonitor;
	
	private final FileModifiedHandler fileModifiedHandler;

	public FileModifiedChecker(FileMonitor fileMonitor, FileModifiedHandler fileModifiedHandler) {
		this.fileMonitor = fileMonitor;
		this.fileModifiedHandler = fileModifiedHandler;
	}

	public boolean callHandlerIfModified() {
		boolean wasModified = fileMonitor.wasModified();
		if (wasModified) {
			fileModifiedHandler.handleFileModified(fileMonitor.getFile());
			fileMonitor.reset();
		}
		return wasModified;
	}
	
}