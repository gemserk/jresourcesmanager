package com.gemserk.resources;

import java.awt.Image;

/**
 * One option could be to have a custom ResourceManager for your type of resource, as the next example:
 */
public class ImageResourceManager extends ResourceManager {

	public <T extends Image> Resource<T> get(Object id) {
		return super.get(id, Image.class);
	}

	@SuppressWarnings("unchecked")
	public void registerLoader(ResourceLoaderProvider resourceLoaderProvider) {
		super.registerLoaderProvider(Image.class, resourceLoaderProvider);
	}

}