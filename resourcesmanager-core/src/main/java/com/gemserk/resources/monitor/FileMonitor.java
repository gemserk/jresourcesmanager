package com.gemserk.resources.monitor;

import com.gemserk.resources.monitor.handlers.FileHandler;

public class FileMonitor {

	private final FileInformation fileInformation;
	
	private final FileHandler fileHandler;

	public FileMonitor(FileInformation fileInformation, FileHandler fileHandler) {
		this.fileInformation = fileInformation;
		this.fileHandler = fileHandler;
	}

	public boolean callHandlerIfModified() {
		boolean wasModified = fileInformation.wasModified();
		if (wasModified) {
			fileHandler.onFileModified(fileInformation.getFile());
			fileInformation.update();
		}
		return wasModified;
	}
	
}