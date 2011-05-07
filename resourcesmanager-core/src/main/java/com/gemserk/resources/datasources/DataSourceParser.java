package com.gemserk.resources.datasources;

import com.gemserk.resources.datasources.DataSourceProvider.Source;

/**
 * Parses a String and return a corresponding DataSource. 
 */
public class DataSourceParser {
	
	DataSourceProvider dataSourceProvider = new DataSourceProvider(Source.ClassPath);
	
	public void setDataSourceProvider(DataSourceProvider dataSourceProvider) {
		this.dataSourceProvider = dataSourceProvider;
	}
	
	public DataSource parse(String url) {
		
		if (url.startsWith("classpath://")) {
			String path = url.replaceAll("classpath://", "");
			return DataSourceFactory.classPathDataSource(path);
		}
		
		if (url.startsWith("file://")) {
			String path = url.replaceAll("file://", "");
			return DataSourceFactory.fileSystemDataSource(path);
		}
		
		if (url.startsWith("http://")) {
			return DataSourceFactory.remoteDataSource(url);
		}
		
		// try each base location: classpath, filesystem, etc, instead returning default? 
		return dataSourceProvider.get(url);
	}
	
}