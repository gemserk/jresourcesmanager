package com.gemserk.resources.datasources;

import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.hamcrest.core.IsNull;
import org.junit.Test;


public class FileSystemDataSourceTest {

	@Test
	public void testGetInputStream() throws URISyntaxException {
		
		URL resource = Thread.currentThread().getContextClassLoader().getResource("test.properties");
		// InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties");

		FileSystemDataSource dataSource = new FileSystemDataSource(new File(resource.toURI()).getAbsolutePath());
		
		assertThat(dataSource.getInputStream(), IsNull.notNullValue());
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testFailToGetInputStream() {
		FileSystemDataSource dataSource = new FileSystemDataSource("not.txt");
		dataSource.getInputStream();
	}
}
