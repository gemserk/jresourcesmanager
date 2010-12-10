package com.gemserk.resources.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.gemserk.resources.monitor.handlers.FileModifiedHandler;


public class FileModifiedCheckerTest {
	
	@Test
	public void shouldNotCallHanlerWhenFileNotModified() {
		FileMonitor fileMonitor = createMock(FileMonitor.class);
		FileModifiedHandler fileModifiedHandler = createMock(FileModifiedHandler.class);
		expect(fileMonitor.wasModified()).andReturn(false);
		replay(fileMonitor, fileModifiedHandler);
		
		FileModifiedChecker fileModifiedChecker = new FileModifiedChecker(fileMonitor, fileModifiedHandler);
		assertFalse(fileModifiedChecker.callHandlerIfModified());
		
		verify(fileMonitor, fileModifiedHandler);
	}

	@Test
	public void shouldCallHandlerWhenFileModified() {
		FileMonitor fileMonitor = createMock(FileMonitor.class);
		File file = createMock(File.class);
		FileModifiedHandler fileModifiedHandler = createMock(FileModifiedHandler.class);
		expect(fileMonitor.wasModified()).andReturn(true);
		expect(fileMonitor.getFile()).andReturn(file);
		fileModifiedHandler.handleFileModified(file);
		fileMonitor.reset();
		replay(fileMonitor, fileModifiedHandler);
		
		FileModifiedChecker fileModifiedChecker = new FileModifiedChecker(fileMonitor, fileModifiedHandler);
		assertTrue(fileModifiedChecker.callHandlerIfModified());
		
		verify(fileMonitor, fileModifiedHandler);
	}


}
