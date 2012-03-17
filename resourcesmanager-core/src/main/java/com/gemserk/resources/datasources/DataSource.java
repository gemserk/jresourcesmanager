package com.gemserk.resources.datasources;

import java.io.InputStream;
import java.net.URI;

/**
 * Provides a way to abstract the location of the real data.
 * 
 * @author acoppes
 * 
 */
public interface DataSource {

	/**
	 * Returns a stream to the data.
	 */
	InputStream getInputStream();

	/**
	 * Returns how this DataSource identifies the data.
	 */
	String getResourceName();

	/**
	 * Returns an URI to let build a File in an easy way (not always implementable)
	 */
	URI getUri();

}