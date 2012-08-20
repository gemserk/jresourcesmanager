package com.gemserk.resources.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class FileMonitorTest {

	@Test
	public void shouldNotBeModifiedIfFileModifiedDateHasNotChanged() {
		File file = createMock(File.class);
		expect(file.lastModified()).andReturn(1L).times(2);
		replay(file);

		FileInformation fileInformation = new FileInformationImpl(file);
		assertEquals(false, fileInformation.wasModified());

		verify(file);
	}

	@Test
	public void shouldBeModifiedIfFileModifiedDateChanged() {
		File file = createMock(File.class);
		expect(file.lastModified()).andReturn(1L);
		expect(file.lastModified()).andReturn(2L);
		replay(file);

		FileInformation fileInformation = new FileInformationImpl(file);
		assertEquals(true, fileInformation.wasModified());

		verify(file);
	}


}
