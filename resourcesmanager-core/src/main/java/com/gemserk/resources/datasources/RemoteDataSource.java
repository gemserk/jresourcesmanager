package com.gemserk.resources.datasources;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a way to access data located on a remote storage by using a URL.
 * 
 * @author acoppes
 * 
 */
public class RemoteDataSource implements DataSource {

	protected static final Logger logger = LoggerFactory.getLogger(RemoteDataSource.class);

	private final String path;

	private final String resourceName;

	public RemoteDataSource(String url) {
		this.path = url;
		this.resourceName = url;
	}

	public InputStream getInputStream() {
		try {
			if (logger.isInfoEnabled())
				logger.info("loading stream from " + getResourceName());
			return new URL(path).openStream();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			// some other exception ? to let the user do something in this case...
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getResourceName() {
		return resourceName;
	}

	@Override
	public URI getUri() {
		try {
			return new URL(path).toURI();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resourceName == null) ? 0 : resourceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemoteDataSource other = (RemoteDataSource) obj;
		if (resourceName == null) {
			if (other.resourceName != null)
				return false;
		} else if (!resourceName.equals(other.resourceName))
			return false;
		return true;
	}

}