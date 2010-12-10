package com.gemserk.resources.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;

import java.net.MalformedURLException;

import org.junit.Test;

import com.gemserk.resources.monitor.ResourceMonitor;
import com.gemserk.resources.monitor.ResourcesMonitorImpl;

public class ResourcesMonitorTest {

	@Test
	public void shouldCheckIfMonitoredResouceMonitorShouldReload() throws MalformedURLException {
		ResourceMonitor resourceMonitor = createMock(ResourceMonitor.class);
		expect(resourceMonitor.reloadIfModified()).andReturn(false);
		replay(resourceMonitor);

		ResourcesMonitor resourcesMonitorImpl = new ResourcesMonitorImpl();

		resourcesMonitorImpl.monitor(resourceMonitor);
		resourcesMonitorImpl.reloadModifiedResources();

		verify(resourceMonitor);
	}

	@Test
	public void testWithTwoResourceMonitors() throws MalformedURLException {
		ResourceMonitor resourceMonitor = createMock(ResourceMonitor.class);
		expect(resourceMonitor.reloadIfModified()).andReturn(false);

		ResourceMonitor resourceMonitor2 = createMock(ResourceMonitor.class);
		expect(resourceMonitor2.reloadIfModified()).andReturn(false);
		
		replay(resourceMonitor, resourceMonitor2);

		ResourcesMonitor resourcesMonitorImpl = new ResourcesMonitorImpl();

		resourcesMonitorImpl.monitor(resourceMonitor);
		resourcesMonitorImpl.monitor(resourceMonitor2);
		resourcesMonitorImpl.reloadModifiedResources();

		verify(resourceMonitor, resourceMonitor2);
	}

}
