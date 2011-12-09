package com.gemserk.resources.monitor;

import com.gemserk.resources.monitor.handlers.FileStatusChangedHandler;

public class FileMonitor {

	private final FileInformation fileInformation;
	
	private final FileStatusChangedHandler fileStatusChangedHandler;

	public FileMonitor(FileInformation fileInformation, FileStatusChangedHandler fileStatusChangedHandler) {
		this.fileInformation = fileInformation;
		this.fileStatusChangedHandler = fileStatusChangedHandler;
	}

	public boolean callHandlerIfModified() {
		boolean wasModified = fileInformation.wasModified();
		if (wasModified) {
			fileStatusChangedHandler.onFileModified(fileInformation.getFile());
			fileInformation.update();
		}
		return wasModified;
	}
	
}