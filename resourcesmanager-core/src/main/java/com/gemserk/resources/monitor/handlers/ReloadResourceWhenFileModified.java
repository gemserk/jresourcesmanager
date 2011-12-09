package com.gemserk.resources.monitor.handlers;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.Resource;

@SuppressWarnings("rawtypes")
public class ReloadResourceWhenFileModified extends FileStatusChangedHandlerAdapter {

	protected static final Logger logger = LoggerFactory.getLogger(ReloadResourceWhenFileModified.class);

	private final Resource resource;

	public ReloadResourceWhenFileModified(Resource resource) {
		this.resource = resource;
	}

	@Override
	public void onFileModified(File file) {
		resource.reload();
		if (logger.isInfoEnabled())
			logger.info("Reloading resource from file " + file);
	}

}