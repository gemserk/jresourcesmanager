package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ResourceManager {
	
	Map<Class, ResourceLoaderProvider> resourceLoaderProviders = new HashMap<Class, ResourceLoaderProvider>();

	Map<Object, Resource> cachedResources = new HashMap<Object, Resource>();

	public void registerLoader(Class clazz, ResourceLoaderProvider resourceLoaderProvider) {
		resourceLoaderProviders.put(clazz, resourceLoaderProvider);
	}

	public <T, K extends T> Resource<K> get(Object id, Class<T> clazz) {
		if (!cachedResources.containsKey(id)) {
			if (!resourceLoaderProviders.containsKey(clazz))
				throw new ResourceLoaderNotRegisteredException("Loader not registered for class " + clazz.getCanonicalName());
			ResourceLoaderProvider resourceLoaderProvider = resourceLoaderProviders.get(clazz);
			ResourceLoader resourceLoader = resourceLoaderProvider.get(id);
			Resource resource = new Resource(resourceLoader.load());
			
			if (!resourceLoader.isCached())
				return resource;
			
			cachedResources.put(id, resource);
		}
		return cachedResources.get(id);
	}

	public <T> void reload(Object id, Class<T> clazz) {
		if (!cachedResources.containsKey(id)) {

//			ResourceLoaderProvider resourceLoaderProvider = resourceLoaderProviders.get(clazz);
//			ResourceLoader resourceLoader = resourceLoaderProvider.get(id);
//			
//			if (!resourceLoader.isCached())
//				return;
			
			throw new RuntimeException("Resource with id " + id.toString() + " not found, maybe is not a cached resource.");
		}

		Resource resource = cachedResources.get(id);
		
		ResourceLoaderProvider resourceLoaderProvider = resourceLoaderProviders.get(clazz);
		ResourceLoader resourceLoader = resourceLoaderProvider.get(id);
		
		// call dispose if the resource has custom dispose logic
		resourceLoader.dispose(resource.get());
		
		resource.set(resourceLoader.load());
		
		// for each dependent resource, reload it... without order for now 
	}
	
	/**
	 * Reloads all cached resources.
	 */
	public void reloadAll() {
		for (Object key : cachedResources.keySet()) {
			Resource cachedResource = cachedResources.get(key);
			reload(key, cachedResource.get().getClass());
		}
	}
	
}