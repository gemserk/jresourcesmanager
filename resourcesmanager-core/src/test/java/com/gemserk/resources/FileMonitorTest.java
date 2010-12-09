package com.gemserk.resources;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.gemserk.resources.monitor.FileMonitor;
import com.gemserk.resources.monitor.FileMonitorImpl;

public class FileMonitorTest {

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


}
