package com.gemserk.resources.slick;


import com.gemserk.resources.ResourceManager;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class PropertiesBaseLoader {

	protected ResourceManager resourceManager;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}
	
}