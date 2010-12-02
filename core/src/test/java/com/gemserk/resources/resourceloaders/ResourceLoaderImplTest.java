package com.gemserk.resources.resourceloaders;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gemserk.resources.Resource;
import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings("unchecked")
public class ResourceLoaderImplTest {

	@Test
	public void shouldReturnResourceIfNotDeferredLoader() {
		String data = "HELLO";

		DataLoader<String> dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data);
		replay(dataLoader);

		ResourceLoader<String> resourceLoader = new ResourceLoaderImpl<String>(dataLoader);

		Resource<String> resource = resourceLoader.load();
		assertEquals(data, resource.get());

		verify(dataLoader);
	}

	@Test
	public void shouldReturnDeferredResourceIfDeferredLoader() {
		DataLoader<String> dataLoader = createMock(DataLoader.class);
		replay(dataLoader);

		ResourceLoader<String> resourceLoader = new ResourceLoaderImpl<String>(dataLoader, true);
		resourceLoader.load();

		verify(dataLoader);
	}

}
