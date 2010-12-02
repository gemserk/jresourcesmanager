package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;

public class DeferredResourceTest {

	@Test
	public void dataShouldNotBeLoadedBeforeGetCalledFirstTime() {

		String data = "helloworld";

		DataLoader dataLoader = createMock(DataLoader.class);
		// expect(resourceLoader.load()).andReturn(data);
		replay(dataLoader);

		Resource<String> defferedResource = new DeferredResource<String>(dataLoader);

		verify(dataLoader);

	}

	@Test
	public void dataShouldBeLoadedWhenGetCalled() {
		String data = "helloworld";

		DataLoader dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data);
		replay(dataLoader);

		Resource<String> defferedResource = new DeferredResource<String>(dataLoader);
		String actualData = defferedResource.get();
		
		assertThat(actualData, IsEqual.equalTo(data));

		verify(dataLoader);
	}
	
	@Test
	public void dataShouldBeLoadedOnlyFirstTimeGetCalled() {
		String data = "helloworld";

		DataLoader dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data).once();
		replay(dataLoader);

		Resource<String> defferedResource = new DeferredResource<String>(dataLoader);
		defferedResource.get();
		defferedResource.get();
		
		verify(dataLoader);
	}

}
