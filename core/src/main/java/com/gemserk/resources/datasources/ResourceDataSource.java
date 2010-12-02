package com.gemserk.resources.datasources;

import java.io.InputStream;

public interface ResourceDataSource {

	InputStream getInputStream();

	String getResourceName();

}