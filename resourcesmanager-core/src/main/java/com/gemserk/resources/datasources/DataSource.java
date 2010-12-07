package com.gemserk.resources.datasources;

import java.io.InputStream;
import java.net.URI;

public interface DataSource {

	InputStream getInputStream();

	String getResourceName();
	
	URI getUri();

}