package com.gemserk.resources.slick;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.monitor.FileUtils;
import com.gemserk.resources.monitor.FilesMonitor;
import com.gemserk.resources.monitor.handlers.ReloadResourceWhenFileModified;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class PropertiesLoader {

	protected ResourceManager resourceManager;
	
	protected FilesMonitor filesMonitor;

	public PropertiesLoader() {
		super();
	}

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Inject
	public void setFilesMonitor(FilesMonitor filesMonitor) {
		this.filesMonitor = filesMonitor;
	}
	
	public void monitor(String id, String file) {
		filesMonitor.monitor(FileUtils.classPathFile(file), new ReloadResourceWhenFileModified(resourceManager.get(id)));
	}

}