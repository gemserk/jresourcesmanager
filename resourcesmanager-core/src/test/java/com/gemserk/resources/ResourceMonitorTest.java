package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ResourceMonitorTest {
	
	@Test
	public void shouldReloadAllCachedResources() {
		
		String resourceId = "ID";

		ResourceManager resourceManager = createMock(ResourceManager.class);
		Resource resource = createMock(Resource.class);
		DataLoader dataLoader = createMock(DataLoader.class);
		
		resourceManager.add(resourceId, dataLoader);
		
		expect(resourceManager.get(resourceId)).andReturn(resource);
		resource.reload();
		
		replay(resourceManager, resource);
		
		ResourcesMonitorImpl resourcesMonitorImpl = new ResourcesMonitorImpl(resourceManager);
		
		resourcesMonitorImpl.add(resourceId, dataLoader);
		Resource actualResource = resourcesMonitorImpl.get(resourceId);
		
		assertSame(resource, actualResource);
		
		resourcesMonitorImpl.reloadAll();
		
		verify(resourceManager, resource);
		
	}

}
