package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;

import java.net.MalformedURLException;

import org.junit.Test;

import com.gemserk.resources.monitor.ResourceMonitor;
import com.gemserk.resources.monitor.ResourcesMonitor;

public class ResourcesMonitorTest {

	@Test
	public void shouldCheckIfMonitoredResouceMonitorShouldReload() throws MalformedURLException {
		ResourceMonitor resourceMonitor = createMock(ResourceMonitor.class);
		expect(resourceMonitor.reloadIfModified()).andReturn(false);
		replay(resourceMonitor);

		ResourcesMonitor resourcesMonitor = new ResourcesMonitor();

		resourcesMonitor.monitor(resourceMonitor);
		resourcesMonitor.reloadModifiedResources();

		verify(resourceMonitor);
	}

	@Test
	public void testWithTwoResourceMonitors() throws MalformedURLException {
		ResourceMonitor resourceMonitor = createMock(ResourceMonitor.class);
		expect(resourceMonitor.reloadIfModified()).andReturn(false);

		ResourceMonitor resourceMonitor2 = createMock(ResourceMonitor.class);
		expect(resourceMonitor2.reloadIfModified()).andReturn(false);
		
		replay(resourceMonitor, resourceMonitor2);

		ResourcesMonitor resourcesMonitor = new ResourcesMonitor();

		resourcesMonitor.monitor(resourceMonitor);
		resourcesMonitor.monitor(resourceMonitor2);
		resourcesMonitor.reloadModifiedResources();

		verify(resourceMonitor, resourceMonitor2);
	}

}
