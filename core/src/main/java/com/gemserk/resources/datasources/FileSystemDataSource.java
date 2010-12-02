package com.gemserk.resources.datasources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileSystemDataSource implements ResourceDataSource {

	private final String url;
	
	public String getUrl() {
		return url;
	}

	public FileSystemDataSource(String url) {
		this.url = url;
	}

	public InputStream getInputStream() {
		try {
			return new FileInputStream(new File(getUrl()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getResourceName() {
		return "file://" + getUrl();
	}

}