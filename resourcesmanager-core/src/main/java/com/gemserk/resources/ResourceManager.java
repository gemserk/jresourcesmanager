package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

/**
 * Provides a common way to handle Resources creation and destruction.
 * 
 * @author acoppes
 */
public interface ResourceManager<K> {

	/**
	 * @param id
	 *            The resource identifier.
	 * @param dataLoader
	 *            The data loader which specifies how to load/unload the resource.
	 */
	void add(K id, DataLoader dataLoader);

	/**
	 * Adds a new volatile resource.
	 * 
	 * @param id
	 *            The resource identifier.
	 * @param dataLoader
	 *            The data loader which specifies how to load/unload the resource.
	 */
	void addVolatile(K id, DataLoader dataLoader);

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
	 * Unloads all created resources by calling each resource.unload() method, it doesn't remove declared resources from the manager.
	 */
	void unloadAll();

	/**
	 * Returns how many resources are registered, useful to iterate with getResource(int index).
	 */
	int getResourcesCount();

	/**
	 * Returns a Resource given an index.
	 * 
	 * @param index
	 *            The index of the Resource.
	 */
	<T> Resource<T> getResourceFromIndex(int index);

	/**
	 * Returns the key for the resource in the specified index.
	 */
	K getKeyFromIndex(int index);

}