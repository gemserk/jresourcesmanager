package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.Resource;

/**
 * Defines a way to load a Resource.
 * 
 * @author acoppes
 * 
 */
public interface ResourceLoader<T> {

	/**
	 * Loads and returns a Resource
	 */
	Resource<T> load();

}