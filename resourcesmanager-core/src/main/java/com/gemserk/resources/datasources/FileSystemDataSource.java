package com.gemserk.resources.datasources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FileSystemDataSource implements DataSource {

	private final String path;

	private final String resourceName;

	public FileSystemDataSource(String path) {
		this.path = path;
		this.resourceName = "file://" + path;
	}

	public InputStream getInputStream() {
		try {
			return new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
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
		FileSystemDataSource other = (FileSystemDataSource) obj;
		if (resourceName == null) {
			if (other.resourceName != null)
				return false;
		} else if (!resourceName.equals(other.resourceName))
			return false;
		return true;
	}

}