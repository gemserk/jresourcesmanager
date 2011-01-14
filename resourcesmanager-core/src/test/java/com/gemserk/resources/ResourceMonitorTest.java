package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertSame;


import org.junit.Test;

import com.gemserk.resources.resourceloaders.ResourceLoader;

@SuppressWarnings("unchecked")
public class ResourceMonitorTest {
	
	@Test
	public void shouldReloadAllCachedResources() {
		
		String resourceId = "ID";

		ResourceManager resourceManager = createMock(ResourceManager.class);
		Resource resource = createMock(Resource.class);
		ResourceLoader resourceLoader = createMock(ResourceLoader.class);
		
		resourceManager.add(resourceId, resourceLoader);
		
		expect(resourceManager.get(resourceId)).andReturn(resource);
		resource.reload();
		
		replay(resourceManager, resource);
		
		ResourcesMonitorImpl resourcesMonitorImpl = new ResourcesMonitorImpl(resourceManager);
		
		resourcesMonitorImpl.add(resourceId, resourceLoader);
		Resource actualResource = resourcesMonitorImpl.get(resourceId);
		
		assertSame(resource, actualResource);
		
		resourcesMonitorImpl.reloadAll();
		
		verify(resourceManager, resource);
		
	}

}
