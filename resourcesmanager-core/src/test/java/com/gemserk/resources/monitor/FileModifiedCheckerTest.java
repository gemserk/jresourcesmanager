package com.gemserk.resources.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.gemserk.resources.monitor.handlers.FileHandler;


public class FileModifiedCheckerTest {
	
	@Test
	public void shouldNotCallHanlerWhenFileNotModified() {
		FileInformation fileInformation = createMock(FileInformation.class);
		FileHandler fileHandler = createMock(FileHandler.class);
		expect(fileInformation.wasModified()).andReturn(false);
		replay(fileInformation, fileHandler);
		
		FileMonitor fileMonitor = new FileMonitor(fileInformation, fileHandler);
		assertFalse(fileMonitor.callHandlerIfModified());
		
		verify(fileInformation, fileHandler);
	}

	@Test
	public void shouldCallHandlerWhenFileModified() {
		FileInformation fileInformation = createMock(FileInformation.class);
		File file = createMock(File.class);
		FileHandler fileHandler = createMock(FileHandler.class);
		expect(fileInformation.wasModified()).andReturn(true);
		expect(fileInformation.getFile()).andReturn(file);
		fileHandler.onFileModified(file);
		fileInformation.update();
		replay(fileInformation, fileHandler);
		
		FileMonitor fileMonitor = new FileMonitor(fileInformation, fileHandler);
		assertTrue(fileMonitor.callHandlerIfModified());
		
		verify(fileInformation, fileHandler);
	}


}
