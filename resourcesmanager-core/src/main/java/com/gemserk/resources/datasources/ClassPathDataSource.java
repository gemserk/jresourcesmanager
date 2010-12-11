package com.gemserk.resources.datasources;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathDataSource implements DataSource {

	protected static final Logger logger = LoggerFactory.getLogger(ClassPathDataSource.class);

	private final String path;

	private final String resourceName;

	public ClassPathDataSource(String path) {
		this.path = path;
		this.resourceName = "classpath://" + path;
	}

	public InputStream getInputStream() {
		if (logger.isInfoEnabled())
			logger.info("loading stream " + getResourceName());
		return getClass().getClassLoader().getResourceAsStream(path);
	}

	@Override
	public String getResourceName() {
		return resourceName;
	}

	@Override
	public URI getUri() {
		try {
			return getClass().getClassLoader().getResource(path).toURI();
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
		ClassPathDataSource other = (ClassPathDataSource) obj;
		if (resourceName == null) {
			if (other.resourceName != null)
				return false;
		} else if (!resourceName.equals(other.resourceName))
			return false;
		return true;
	}

}