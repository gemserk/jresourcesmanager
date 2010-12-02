/**
 * 
 */
package com.gemserk.resources.streams;

import java.io.InputStream;

public abstract class ResourceStream {

	protected final String url;

	public String getUrl() {
		return url;
	}

	public ResourceStream(String url) {
		this.url = url;
	}

	public abstract InputStream getInputStream();

	public abstract String getResourceName();

}