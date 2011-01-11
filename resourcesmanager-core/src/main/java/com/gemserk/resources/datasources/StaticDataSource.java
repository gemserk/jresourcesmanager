package com.gemserk.resources.datasources;

import java.io.InputStream;
import java.net.URI;

public class StaticDataSource implements DataSource {
	
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
		return inputStream;
	}
}