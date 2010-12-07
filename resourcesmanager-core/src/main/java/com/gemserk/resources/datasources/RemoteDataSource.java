package com.gemserk.resources.datasources;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteDataSource implements DataSource {

	protected static final Logger logger = LoggerFactory.getLogger(RemoteDataSource.class);

	private final String url;

	String getUrl() {
		return url;
	}

	public RemoteDataSource(String url) {
		this.url = url;
	}

	public InputStream getInputStream() {
		try {
			if (logger.isInfoEnabled())
				logger.info("loading stream from " + getResourceName());
			return new URL(getUrl()).openStream();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			// some other exception ? to let the user do something in this case...
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getResourceName() {
		return getUrl();
	}

	@Override
	public URI getUri() {
		try {
			return new URL(getUrl()).toURI();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}