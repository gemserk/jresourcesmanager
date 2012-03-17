package com.gemserk.resources.datasources;

/**
 * Provides factory methods to build DataSources.
 * 
 * @author acoppes
 * 
 */
public class DataSourceFactory {

	/**
	 * Returns a DataSource implementation which uses the classpath to return the data.
	 * 
	 * @param path
	 *            The path to be used to build the DataSource.
	 */
	public static DataSource classPathDataSource(String path) {
		return new ClassPathDataSource(path);
	}

	/**
	 * Returns a DataSource implementation which uses the file system to return the data.
	 * 
	 * @param path
	 *            The path to be used to build the DataSource.
	 */
	public static DataSource fileSystemDataSource(String path) {
		return new FileSystemDataSource(path);
	}

	/**
	 * Returns a DataSource implementation which uses an URL to return remote data.
	 * 
	 * @param url
	 *            The URL to be used to build the DataSource.
	 */
	public static DataSource remoteDataSource(String url) {
		return new RemoteDataSource(url);
	}

}
