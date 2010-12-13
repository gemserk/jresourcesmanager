package com.gemserk.resources.datasources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.datasources.DataSource;
import com.gemserk.resources.datasources.DataSourceParser;
import com.gemserk.resources.datasources.FileSystemDataSource;
import com.gemserk.resources.datasources.InvalidDataSourceException;
import com.gemserk.resources.datasources.RemoteDataSource;

public class DataSourceParserTest {

	@Test(expected=InvalidDataSourceException.class)
	public void shouldReturnFailWithExceptionWhenInvalidUrl() {
		new DataSourceParser().parse("");
	}
	
	@Test
	public void shouldReturnClassPathDataSource() {
		String path = "assets/images/image.png";
		DataSource dataSource = new DataSourceParser().parse("classpath://" + path);
		assertTrue(dataSource instanceof ClassPathDataSource);
		assertEquals("classpath://" + path, dataSource.getResourceName());
	}
	
	@Test
	public void shouldReturnFileSystemDataSource() {
		String path = "assets/images/image.png";
		DataSource dataSource = new DataSourceParser().parse("file://" + path);
		assertTrue(dataSource instanceof FileSystemDataSource);
		assertEquals("file://" + path, dataSource.getResourceName());
	}
	
	@Test
	public void shouldReturnRemoteDataSource() {
		String path = "assets/images/image.png";
		DataSource dataSource = new DataSourceParser().parse("http://gemserk.com/" + path);
		assertTrue(dataSource instanceof RemoteDataSource);
		assertEquals("http://gemserk.com/" + path, dataSource.getResourceName());
	}

}
