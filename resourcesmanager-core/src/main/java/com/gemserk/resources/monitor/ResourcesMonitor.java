package com.gemserk.resources.monitor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;

@SuppressWarnings("unchecked")
public class ResourcesMonitor {

	protected static final Logger logger = LoggerFactory.getLogger(ResourcesMonitor.class);

	private ResourceManager resourceManager;

	private Map<String, FileMonitor> fileMonitors = new HashMap<String, FileMonitor>();

	public ResourcesMonitor(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void monitor(String resourceId, FileMonitor fileMonitor) {
		fileMonitors.put(resourceId, fileMonitor);
	}

	public void reloadModifiedResources() {
		for (String key : fileMonitors.keySet()) {
			FileMonitor fileMonitor = fileMonitors.get(key);
			if (fileMonitor.wasModified()) {
				if (logger.isDebugEnabled())
					logger.debug("file for resource " + key + " was modified");
				Resource resource = resourceManager.get(key);
				resource.reload();
				fileMonitor.reset();
			}
		}
	}

}