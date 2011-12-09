package com.gemserk.resources.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.gemserk.resources.monitor.handlers.FileStatusChangedHandler;


public class FileModifiedCheckerTest {
	
	@Test
	public void shouldNotCallHanlerWhenFileNotModified() {
		FileInformation fileInformation = createMock(FileInformation.class);
		FileStatusChangedHandler fileStatusChangedHandler = createMock(FileStatusChangedHandler.class);
		expect(fileInformation.wasModified()).andReturn(false);
		replay(fileInformation, fileStatusChangedHandler);
		
		FileMonitor fileMonitor = new FileMonitor(fileInformation, fileStatusChangedHandler);
		assertFalse(fileMonitor.callHandlerIfModified());
		
		verify(fileInformation, fileStatusChangedHandler);
	}

	@Test
	public void shouldCallHandlerWhenFileModified() {
		FileInformation fileInformation = createMock(FileInformation.class);
		File file = createMock(File.class);
		FileStatusChangedHandler fileStatusChangedHandler = createMock(FileStatusChangedHandler.class);
		expect(fileInformation.wasModified()).andReturn(true);
		expect(fileInformation.getFile()).andReturn(file);
		fileStatusChangedHandler.onFileModified(file);
		fileInformation.update();
		replay(fileInformation, fileStatusChangedHandler);
		
		FileMonitor fileMonitor = new FileMonitor(fileInformation, fileStatusChangedHandler);
		assertTrue(fileMonitor.callHandlerIfModified());
		
		verify(fileInformation, fileStatusChangedHandler);
	}


}
