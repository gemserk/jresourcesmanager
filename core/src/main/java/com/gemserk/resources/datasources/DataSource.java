package com.gemserk.resources.datasources;

import java.io.InputStream;

public interface DataSource {

	InputStream getInputStream();

	String getResourceName();

}