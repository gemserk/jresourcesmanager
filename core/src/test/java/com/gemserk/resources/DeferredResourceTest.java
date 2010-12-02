package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class DeferredResourceTest {

	@Test
	public void dataShouldNotBeLoadedBeforeGetCalledFirstTime() {

		String data = "helloworld";

		ResourceLoader resourceLoader = createMock(ResourceLoader.class);
		// expect(resourceLoader.load()).andReturn(data);
		replay(resourceLoader);

		Resource<String> defferedResource = new DeferredResource<String>(resourceLoader);

		verify(resourceLoader);

	}

	@Test
	public void dataShouldBeLoadedWhenGetCalled() {
		String data = "helloworld";

		ResourceLoader resourceLoader = createMock(ResourceLoader.class);
		expect(resourceLoader.load()).andReturn(data);
		replay(resourceLoader);

		Resource<String> defferedResource = new DeferredResource<String>(resourceLoader);
		String actualData = defferedResource.get();
		
		assertThat(actualData, IsEqual.equalTo(data));

		verify(resourceLoader);
	}
	
	@Test
	public void dataShouldBeLoadedOnlyFirstTimeGetCalled() {
		String data = "helloworld";

		ResourceLoader resourceLoader = createMock(ResourceLoader.class);
		expect(resourceLoader.load()).andReturn(data).once();
		replay(resourceLoader);

		Resource<String> defferedResource = new DeferredResource<String>(resourceLoader);
		defferedResource.get();
		defferedResource.get();
		
		verify(resourceLoader);
	}

}
