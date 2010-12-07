package com.gemserk.resources.datasources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemDataSource implements DataSource {

	protected static final Logger logger = LoggerFactory.getLogger(FileSystemDataSource.class);

	private final String file;

	String getFile() {
		return file;
	}

	public FileSystemDataSource(String file) {
		this.file = file;
	}

	public InputStream getInputStream() {
		try {
			if (logger.isInfoEnabled())
				logger.info("loading stream " + getResourceName());
			return new FileInputStream(new File(getFile()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getResourceName() {
		return "file://" + getFile();
	}

	@Override
	public URI getUri() {
		try {
			return new URL(getFile()).toURI();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}