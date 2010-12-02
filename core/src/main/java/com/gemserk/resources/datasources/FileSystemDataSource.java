package com.gemserk.resources.datasources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemDataSource implements DataSource {
	
	protected static final Logger logger = LoggerFactory.getLogger(FileSystemDataSource.class);

	private final String url;
	
	public String getUrl() {
		return url;
	}

	public FileSystemDataSource(String url) {
		this.url = url;
	}

	public InputStream getInputStream() {
		try {
			if (logger.isInfoEnabled())
				logger.info("loading stream " + getResourceName());
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