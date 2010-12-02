package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.same;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings("unchecked")
public class ReloadableResourceTest {
	
	@Test
	public void shouldNotDisposeNullData() {
		String data1 = "HELLO1";
		
		DataLoader<String> dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data1);
		replay(dataLoader);

		ReloadableResource<String> reloadableResource = new ReloadableResource<String>(dataLoader, true);
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

		ReloadableResource<String> reloadableResource = new ReloadableResource<String>(dataLoader, true);
		assertEquals(data1, reloadableResource.get());
		assertEquals(data1, reloadableResource.get());
		reloadableResource.reload();
		assertEquals(data2, reloadableResource.get());

		verify(dataLoader);
	}
	
}
