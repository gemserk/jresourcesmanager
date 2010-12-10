package com.gemserk.resources.monitor;

import java.io.File;

import com.gemserk.resources.datasources.ClassPathDataSource;

public class FileUtils {

	public static File classPathFile(String file) {
		return new File(new ClassPathDataSource(file).getUri());
	}

}