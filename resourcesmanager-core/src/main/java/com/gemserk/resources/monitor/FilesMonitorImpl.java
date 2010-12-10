package com.gemserk.resources.monitor;

import java.io.File;
import java.util.ArrayList;

import com.gemserk.resources.monitor.handlers.FileModifiedHandler;

public class FilesMonitorImpl implements FilesMonitor {

	private ArrayList<FileModifiedChecker> fileModifiedCheckers = new ArrayList<FileModifiedChecker>();

	public void checkModifiedFiles() {
		for (FileModifiedChecker fileModifiedChecker : fileModifiedCheckers)
			fileModifiedChecker.callHandlerIfModified();
	}

	public void monitor(File file, FileModifiedHandler fileModifiedHandler) {
		fileModifiedCheckers.add(new FileModifiedChecker(new FileMonitorImpl(file), fileModifiedHandler));
	}

}