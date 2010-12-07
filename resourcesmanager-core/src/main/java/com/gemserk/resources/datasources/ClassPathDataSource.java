package com.gemserk.resources.datasources;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathDataSource implements DataSource {

	protected static final Logger logger = LoggerFactory.getLogger(ClassPathDataSource.class);

	private final String url;

	String getUrl() {
		return url;
	}

	public ClassPathDataSource(String url) {
		this.url = url;
	}

	public InputStream getInputStream() {
		if (logger.isInfoEnabled())
			logger.info("loading stream " + getResourceName());
		return getClass().getClassLoader().getResourceAsStream(getUrl());
	}

	@Override
	public String getResourceName() {
		return "classpath://" + getUrl();
	}

	@Override
	public URI getUri() {
		try {
			return getClass().getClassLoader().getResource(getUrl()).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}