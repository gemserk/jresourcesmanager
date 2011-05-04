package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.same;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.StaticDataLoader;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ResourceTest {

	@Test
	public void dataShouldNotBeLoadedBeforeGetCalledFirstTimeWhenDeferred() {
		DataLoader dataLoader = createMock(DataLoader.class);
		replay(dataLoader);

		new Resource<String>(dataLoader, true);

		verify(dataLoader);
	}

	@Test
	public void dataShouldBeLoadedOnConstructorWhenNotDeferred() {
		String data = "helloworld";

		DataLoader dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data);
		replay(dataLoader);
		
		new Resource<String>(dataLoader);
		
		verify(dataLoader);
	}

	@Test
	public void dataShouldBeLoadedOnlyWhenGetCalledFirstTimeWhenDeferred() {
		String data = "helloworld";

		DataLoader dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data).once();
		replay(dataLoader);

		Resource<String> defferedResource = new Resource<String>(dataLoader, true);
		defferedResource.get();
		defferedResource.get();

		verify(dataLoader);
	}

	@Test
	public void shouldReturnDataFromDataLoader() {
		String data = "helloworld";

		Resource<String> defferedResource = new Resource<String>(new StaticDataLoader<String>(data));
	    String actualData = defferedResource.get();

		assertThat(actualData, IsEqual.equalTo(data));
	}
	
	@Test
	public void shouldNotDisposeNullData() {
		String data1 = "HELLO1";
		
		DataLoader<String> dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data1);
		replay(dataLoader);

		Resource<String> reloadableResource = new Resource<String>(dataLoader, true);
		reloadableResource.reload();
		
		assertEquals(data1, reloadableResource.get());

		verify(dataLoader);
	}

	@Test
	public void shouldLoadFromDataLoaderWhenReloading() {
		String data1 = "HELLO1";
		String data2 = "HELLO2";

		DataLoader<String> dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data1);
		dataLoader.dispose(same(data1));
		expect(dataLoader.load()).andReturn(data2);
		replay(dataLoader);

		Resource<String> reloadableResource = new Resource<String>(dataLoader, true);
		assertEquals(data1, reloadableResource.get());
		assertEquals(data1, reloadableResource.get());
		reloadableResource.reload();
		assertEquals(data2, reloadableResource.get());

		verify(dataLoader);
	}
}
