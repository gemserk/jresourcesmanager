package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.StaticDataLoader;

/**
 * Provides methods to build a Resources and DataLoaders.
 * 
 * @author acoppes
 * 
 */
public class ResourceFactory {

	/**
	 * Builds a new Resource<T> using a fixed DataLoader<T> which returns always the specified data.
	 * 
	 * @param t
	 *            The data to be used by the Resource.
	 */
	public static <T> Resource<T> resource(T t) {
		return new Resource<T>(staticDataLoader(t));
	}

	/**
	 * Builds a new Resource<T> using the specified DataLoader<T>.
	 * 
	 * @param t
	 *            The data to be used by the Resource.
	 */
	public static <T> Resource<T> resource(DataLoader<T> dataLoader) {
		return new Resource<T>(dataLoader);
	}

	/**
	 * Returns a new fixed DataLoader<T> which returns always the specified data.
	 * 
	 * @param t
	 *            The data to be used by the DataLoader<T>
	 */
	public static <T> DataLoader<T> staticDataLoader(T t) {
		return new StaticDataLoader<T>(t);
	}

}
