package com.gemserk.resources.slick;


import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.monitor.FileMonitorResourceHelper;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class PropertiesBaseLoader {

	protected ResourceManager resourceManager;

	protected FileMonitorResourceHelper fileMonitorResourceHelper;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}
	
	@Inject
	public void setFileMonitorResourceHelper(FileMonitorResourceHelper fileMonitorResourceHelper) {
		this.fileMonitorResourceHelper = fileMonitorResourceHelper;
	}

}