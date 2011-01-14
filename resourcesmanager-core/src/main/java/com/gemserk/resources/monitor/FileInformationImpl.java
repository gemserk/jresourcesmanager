package com.gemserk.resources.monitor;

import java.io.File;

/**
 * @author acoppes
 * A basic implementation of FileInformation which uses file date to know if the file was modified.
 */
public class FileInformationImpl implements FileInformation {

	private final File file;

	private long lastModified;

	public FileInformationImpl(File file) {
		this.file = file;
		update();
	}

	@Override
	public boolean wasModified() {
		return lastModified != file.lastModified();
	}

	@Override
	public void update() {
		lastModified = file.lastModified();
	}
	
	@Override
	public File getFile() {
		return file;
	}

}