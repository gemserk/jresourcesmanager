package com.gemserk.resources.streams;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathResourceStream extends ResourceStream {
	
	protected static final Logger logger = LoggerFactory.getLogger(ClassPathResourceStream.class);

	public ClassPathResourceStream(String url) {
		super(url);
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