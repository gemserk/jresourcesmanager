package com.gemserk.resources.datasources;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathDataSource implements DataSource {
	
	protected static final Logger logger = LoggerFactory.getLogger(ClassPathDataSource.class);

	private final String url;
	
	public String getUrl() {
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

}