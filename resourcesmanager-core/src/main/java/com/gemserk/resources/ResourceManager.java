package com.gemserk.resources;

import com.gemserk.resources.resourceloaders.ResourceLoader;

/**
 * Provides a common way to handle Resources creation and destruction.
 * 
 * @author acoppes
 */
public interface ResourceManager<K> {

	@SuppressWarnings("rawtypes")
	void add(K id, ResourceLoader resourceLoader);

	/**
	 * Returns a Resource identified by id.
	 * 
	 * @param id
	 *            the Resource identifier.
	 */
	<T> Resource<T> get(K id);

	/**
	 * Returns the value of an existent Resource. If the resource doesn't exists it returns null.
	 * 
	 * @param id
	 *            the Resource identifier.
	 * @return the value of the Resource if it exists, null otherwise.
	 */
	<T> T getResourceValue(K id);

	/**
	 * Unloads all created resources.
	 */
	void unloadAll();

}