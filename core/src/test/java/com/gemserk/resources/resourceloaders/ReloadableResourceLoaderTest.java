package com.gemserk.resources.resourceloaders;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gemserk.resources.ReloadableResource;
import com.gemserk.resources.Resource;
import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings("unchecked")
public class ReloadableResourceLoaderTest {

	@Test
	public void shouldReturnResourceIfNotDeferredLoader() {
		String data = "HELLO";

		DataLoader<String> dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(data);
		replay(dataLoader);

		ReloadableResourceLoader<String> resourceLoader = new ReloadableResourceLoaderImpl<String>(dataLoader);

		Resource<String> resource = resourceLoader.load();
		assertTrue(resource instanceof Resource);
		assertEquals(data, resource.get());

		verify(dataLoader);
	}

	@Test
	public void shouldReturnDeferredResourceIfDeferredLoader() {
		DataLoader<String> dataLoader = createMock(DataLoader.class);
		replay(dataLoader);

		ReloadableResourceLoader<String> resourceLoader = new ReloadableResourceLoaderImpl<String>(dataLoader, true);

		Resource<String> resource = resourceLoader.load();
		assertTrue(resource instanceof ReloadableResource);

		verify(dataLoader);
	}

}
