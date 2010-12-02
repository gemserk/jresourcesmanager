package com.gemserk.resources.datasources;

public class DataSourceProvider {

	enum Source {
		ClassPath, FileSystem, Remote,
	}

	final Source source;

	public DataSourceProvider() {
		this(Source.ClassPath);
	}

	public DataSourceProvider(Source source) {
		this.source = source;
	}

	public ResourceDataSource get(String url) {
		switch (source) {
		case FileSystem:
			return new FileSystemDataSource(url);
		case Remote:
			return new RemoteDataSource(url);
		default:
			return new ClassPathDataSource(url);
		}
	}

}