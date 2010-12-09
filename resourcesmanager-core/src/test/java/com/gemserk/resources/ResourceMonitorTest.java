package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gemserk.resources.monitor.FileMonitor;
import com.gemserk.resources.monitor.ResourceMonitor;

@SuppressWarnings("unchecked")
public class ResourceMonitorTest {

	@Test
	public void shouldNotReloadResourceIfFileNotModified() {
		Resource resource = createMock(Resource.class);
		FileMonitor fileMonitor = createMock(FileMonitor.class);
		expect(fileMonitor.wasModified()).andReturn(false);

		replay(fileMonitor, resource);

		ResourceMonitor resourceMonitor = new ResourceMonitor(resource, fileMonitor);

		assertFalse(resourceMonitor.reloadIfModified());

		verify(fileMonitor, resource);
	}

	@Test
	public void shouldReloadResourceIfFileModified() {
		Resource resource = createMock(Resource.class);
		FileMonitor fileMonitor = createMock(FileMonitor.class);
		expect(fileMonitor.wasModified()).andReturn(true);
		fileMonitor.reset();
		resource.reload();

		replay(fileMonitor, resource);

		ResourceMonitor resourceMonitor = new ResourceMonitor(resource, fileMonitor);

		assertTrue(resourceMonitor.reloadIfModified());

		verify(fileMonitor, resource);
	}

}
