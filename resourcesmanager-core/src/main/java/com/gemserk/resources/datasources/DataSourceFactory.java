package com.gemserk.resources.datasources;

public class DataSourceFactory {
	
	public static DataSource classPathDataSource(String path) {
		return new ClassPathDataSource(path);
	}
	
	public static DataSource fileSystemDataSource(String path) {
		return new FileSystemDataSource(path);
	}

	public static DataSource remoteDataSource(String path) {
		return new RemoteDataSource(path);
	}

}
