package com.gemserk.resources.slick;

import java.util.List;
import java.util.Map;

import com.gemserk.resources.ResourceManagerOld;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;

public class SlickImageRegistrator {

	ResourceManagerOld resourceManagerImpl;

	public SlickImageRegistrator(ResourceManagerOld resourceManagerImpl) {
		this.resourceManagerImpl = resourceManagerImpl;
	}

	public void registerImageProviders(List<String> files) {
		for (String file : files)
			resourceManagerImpl.registerDataLoader(file, new SlickImageLoader(file));
	}

	public void registerImageProviders(Map<String, String> files) {
		for (String file : files.keySet())
			resourceManagerImpl.registerDataLoader(file, new SlickImageLoader(files.get(file)));
	}

}