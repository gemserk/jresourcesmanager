package com.gemserk.resources.monitor;

import java.io.File;


public class FileMonitorImpl implements FileMonitor {

	private final File file;

	private long lastModified;

	public FileMonitorImpl(File file) {
		this.file = file;
		reset();
	}

	@Override
	public boolean wasModified() {
		return lastModified != file.lastModified();
	}

	@Override
	public void reset() {
		lastModified = file.lastModified();
	}

}