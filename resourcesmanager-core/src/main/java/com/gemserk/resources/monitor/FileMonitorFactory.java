package com.gemserk.resources.monitor;

import java.io.File;

import com.gemserk.resources.datasources.ClassPathDataSource;

public class FileMonitorFactory {

	public static FileMonitor classPathFileMonitor(String file) {
		return new FileMonitorImpl(new File(new ClassPathDataSource(file).getUri()));
	}
	
	public static FileMonitor fileSystemFileMonitor(String file) {
		return new FileMonitorImpl(new File(file));
	}

}