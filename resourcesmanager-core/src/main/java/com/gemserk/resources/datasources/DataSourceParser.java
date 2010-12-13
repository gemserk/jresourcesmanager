package com.gemserk.resources.datasources;

/**
 * Parses a String and return a corresponding DataSource. 
 */
public class DataSourceParser {
	
	public DataSource parse(String url) {
		
		if (url.startsWith("classpath://")) {
			String path = url.replaceAll("classpath://", "");
			return new ClassPathDataSource(path);
		}
		
		if (url.startsWith("file://")) {
			String path = url.replaceAll("file://", "");
			return new FileSystemDataSource(path);
		}
		
		if (url.startsWith("http://")) {
			return new RemoteDataSource(url);
		}
		
		throw new InvalidDataSourceException("Invalid url " + url);
	}
	
}