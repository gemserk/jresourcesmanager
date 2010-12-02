package com.gemserk.resources.slick;

import java.util.List;
import java.util.Map;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.slick.resourceloaders.SlickImageResourceLoader;

public class SlickImageRegistrator {

	ResourceManager resourceManager;

	public SlickImageRegistrator(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void registerImageProviders(List<String> files) {
		for (String file : files)
			resourceManager.registerResourceLoader(file, new SlickImageResourceLoader(file));
	}

	public void registerImageProviders(Map<String, String> files) {
		for (String file : files.keySet())
			resourceManager.registerResourceLoader(file, new SlickImageResourceLoader(files.get(file)));
	}

}