package com.gemserk.resources.datasources;

import java.io.InputStream;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticDataSource implements DataSource {
	
	protected static final Logger logger = LoggerFactory.getLogger(StaticDataSource.class);

	private final InputStream inputStream;

	private final String resourceName;

	public StaticDataSource(InputStream inputStream, String resourceName) {
		this.inputStream = inputStream;
		this.resourceName = resourceName;
	}

	@Override
	public URI getUri() {
		throw new RuntimeException("getUri not implemented yet");
	}

	@Override
	public String getResourceName() {
		return resourceName;
	}

	@Override
	public InputStream getInputStream() {
		if (logger.isInfoEnabled())
			logger.info("returning static input stream " + getResourceName());
		return inputStream;
	}
}