package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gemserk.resources.monitor.FileMonitor;
import com.gemserk.resources.monitor.FileMonitorImpl;
import com.gemserk.resources.monitor.ResourcesMonitor;

@SuppressWarnings("unchecked")
public class FileMonitorTest {

	class ResourceMonitor {

		private final FileMonitor fileMonitor;

		private final Resource resource;

		public ResourceMonitor(Resource resource, FileMonitor fileMonitor) {
			this.resource = resource;
			this.fileMonitor = fileMonitor;
		}

		public void reloadIfModified() {
			if (fileMonitor.wasModified())
				resource.reload();
		}

	}

	@Test
	public void shouldNotReloadResourceIfFileNotModified() {
		Resource resource = createMock(Resource.class);
		FileMonitor fileMonitor = createMock(FileMonitor.class);
		expect(fileMonitor.wasModified()).andReturn(false);

		replay(fileMonitor, resource);

		ResourceMonitor resourceMonitor = new ResourceMonitor(resource, fileMonitor);

		resourceMonitor.reloadIfModified();

		verify(fileMonitor, resource);
	}

	@Test
	public void shouldReloadResourceIfFileModified() {
		Resource resource = createMock(Resource.class);
		FileMonitor fileMonitor = createMock(FileMonitor.class);
		expect(fileMonitor.wasModified()).andReturn(true);
		resource.reload();

		replay(fileMonitor, resource);

		ResourceMonitor resourceMonitor = new ResourceMonitor(resource, fileMonitor);

		resourceMonitor.reloadIfModified();

		verify(fileMonitor, resource);
	}

	@Test
	public void shouldNotBeModifiedIfFileModifiedDateHasNotChanged() {
		File file = createMock(File.class);
		expect(file.lastModified()).andReturn(1L).times(2);
		replay(file);

		FileMonitor fileMonitor = new FileMonitorImpl(file);
		assertEquals(false, fileMonitor.wasModified());

		verify(file);
	}

	@Test
	public void shouldBeModifiedIfFileModifiedDateChanged() {
		File file = createMock(File.class);
		expect(file.lastModified()).andReturn(1L);
		expect(file.lastModified()).andReturn(2L);
		replay(file);

		FileMonitor fileMonitor = new FileMonitorImpl(file);
		assertEquals(true, fileMonitor.wasModified());

		verify(file);
	}
	
	@Test
	public void test() throws MalformedURLException {
		String resourceId = "ResourceId";

		ResourceManager resourceManager = createMock(ResourceManager.class);
		Resource resource = createMock(Resource.class);
		FileMonitor fileMonitor = createMock(FileMonitor.class);
		expect(resourceManager.get(resourceId)).andReturn(resource);
		expect(fileMonitor.wasModified()).andReturn(true);
		resource.reload();
		fileMonitor.reset();
		
		replay(resourceManager, resource, fileMonitor);
		
		ResourcesMonitor resourcesMonitor = new ResourcesMonitor(resourceManager);
		
		resourcesMonitor.monitor(resourceId, fileMonitor);
		resourcesMonitor.reloadModifiedResources();
		
		verify(resourceManager, resource, fileMonitor);
	}

}
