package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings("unchecked")
public class ResourceManagerOldImplTest {
	
	@Test
	public void shouldLoadAResourceWhenGettingItIfNotDeferred() {
		String data = "Hello World!";

		ResourceManagerOldImpl resourceManagerOldImpl = new ResourceManagerOldImpl();
		
		DataLoader dataLoader = createMock(DataLoader.class);
		dataLoader.setCached(true);
		expect(dataLoader.isCached()).andReturn(true);
		expect(dataLoader.load()).andReturn(data);
		replay(dataLoader);
		
		resourceManagerOldImpl.registerDataLoader("string://helloworld", dataLoader);
		
		Resource<String> stringResource = resourceManagerOldImpl.get("string://helloworld", false);
		
		assertTrue(stringResource instanceof Resource);
		
		verify(dataLoader);
	}
	
	@Test
	public void shouldNotLoadADeferredResourceWhenGettingIt() {
		
		ResourceManagerOldImpl resourceManagerOldImpl = new ResourceManagerOldImpl();
		
		DataLoader dataLoader = createMock(DataLoader.class);
		dataLoader.setCached(true);
		expect(dataLoader.isCached()).andReturn(true);
		replay(dataLoader);
		
		resourceManagerOldImpl.registerDataLoader("string://helloworld", dataLoader);
		
		Resource<String> stringResource = resourceManagerOldImpl.get("string://helloworld", true);
		
		assertTrue(stringResource instanceof DeferredResource);
		
		verify(dataLoader);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldDisposePreviousDataWhenReloadACachedResource() {
		String data = "Hello World!";
		String resourceId = "string://helloworld";

		ResourceManagerOldImpl resourceManagerOldImpl = new ResourceManagerOldImpl();
		
		DataLoader dataLoader = createMock(DataLoader.class);
		dataLoader.setCached(true);
		expect(dataLoader.isCached()).andReturn(true);
		dataLoader.dispose(data);
		expect(dataLoader.load()).andReturn(data).times(2);
		replay(dataLoader);
		
		resourceManagerOldImpl.registerDataLoader(resourceId, dataLoader);
		resourceManagerOldImpl.get(resourceId, false);
		resourceManagerOldImpl.reload(resourceId);
		
		verify(dataLoader);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldDoNothingIfTryingToReloadANotCachedResource() {
		String data = "Hello World!";
		String resourceId = "string://helloworld";

		ResourceManagerOldImpl resourceManagerOldImpl = new ResourceManagerOldImpl();
		
		DataLoader dataLoader = createMock(DataLoader.class);
		dataLoader.setCached(true);
		expect(dataLoader.isCached()).andReturn(false);
		expect(dataLoader.load()).andReturn(data);
		replay(dataLoader);
		
		resourceManagerOldImpl.registerDataLoader(resourceId, dataLoader);
		resourceManagerOldImpl.get(resourceId, false);
		resourceManagerOldImpl.reload(resourceId);
		
		verify(dataLoader);
	}

}
