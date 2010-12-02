package com.gemserk.resources.java2d;

import java.awt.Image;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoaderProvider;
import com.gemserk.resources.ResourceManager;

/**
 * One option could be to have a custom ResourceManager for your type of resource, as the next example:
 */
public class ImageResourceManager extends ResourceManager {

	public <T extends Image> Resource<T> get(Object id) {
		return super.get(id, Image.class);
	}

	@SuppressWarnings("unchecked")
	public void registerLoader(ResourceLoaderProvider resourceLoaderProvider) {
		super.registerLoader(Image.class, resourceLoaderProvider);
	}

}