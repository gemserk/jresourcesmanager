package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class ResourceManagerTest {
	
	@Test
	public void shouldLoadAResourceWhenGettingItIfNotDeferred() {
		String data = "Hello World!";

		ResourceManager resourceManager = new ResourceManager();
		
		ResourceLoader resourceLoader = createMock(ResourceLoader.class);
		resourceLoader.setCached(true);
		expect(resourceLoader.isCached()).andReturn(true);
		expect(resourceLoader.load()).andReturn(data);
		replay(resourceLoader);
		
		resourceManager.registerResourceLoader("string://helloworld", resourceLoader);
		
		Resource<String> stringResource = resourceManager.get("string://helloworld", String.class, false);
		
		assertTrue(stringResource instanceof Resource);
		
		verify(resourceLoader);
	}
	
	@Test
	public void shouldNotLoadADeferredResourceWhenGettingIt() {
		
		ResourceManager resourceManager = new ResourceManager();
		
		ResourceLoader resourceLoader = createMock(ResourceLoader.class);
		resourceLoader.setCached(true);
		expect(resourceLoader.isCached()).andReturn(true);
		replay(resourceLoader);
		
		resourceManager.registerResourceLoader("string://helloworld", resourceLoader);
		
		Resource<String> stringResource = resourceManager.get("string://helloworld", String.class, true);
		
		assertTrue(stringResource instanceof DeferredResource);
		
		verify(resourceLoader);
	}

}
