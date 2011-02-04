package com.gemserk.resources.datasources;

import static org.easymock.classextension.EasyMock.createMock;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsSame;
import org.junit.Test;

public class StaticDataSourceTest {
	
	@Test
	public void testGetInputStream() {
		InputStream inputStream = createMock(InputStream.class);
		StaticDataSource staticDataSource = new StaticDataSource(inputStream, "resource");
		assertThat(staticDataSource.getInputStream(), IsSame.sameInstance(inputStream));
	}
	
	@Test
	public void testGetResourceName() {
		StaticDataSource staticDataSource = new StaticDataSource(null, "resource");
		assertThat(staticDataSource.getResourceName(), IsEqual.equalTo("resource"));
	}

}
