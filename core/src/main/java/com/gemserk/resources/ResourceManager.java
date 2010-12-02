package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public class ResourceManager {
	
	protected static final Logger logger = LoggerFactory.getLogger(ResourceManager.class);
	
	Map<Class, ResourceLoaderProvider> resourceLoaderProviders = new HashMap<Class, ResourceLoaderProvider>();

	Map<Object, Resource> cachedResources = new HashMap<Object, Resource>();
	
	Map<Object, ResourceLoader> resourceLoaders = new HashMap<Object, ResourceLoader>();

	public void registerLoaderProvider(Class clazz, ResourceLoaderProvider resourceLoaderProvider) {
		resourceLoaderProviders.put(clazz, resourceLoaderProvider);
	}
	
	public void registerResourceLoader(Object id, ResourceLoader resourceLoader) {
		registerResourceLoader(id, resourceLoader, true);
	}
	
	public void registerResourceLoader(Object id, ResourceLoader resourceLoader, boolean cached) {
		resourceLoaders.put(id, resourceLoader);
		resourceLoader.setCached(cached);
	}
	
	public <T, K extends T> Resource<K> get(Object id, Class<T> clazz) {
		return get(id, clazz, false);
	}

	public void reload(Object id) {
		if (!cachedResources.containsKey(id)) 
			throw new RuntimeException("Resource with id " + id.toString() + " not found, maybe is not a cached resource.");

		Resource resource = cachedResources.get(id);
		
		ResourceLoader resourceLoader = resourceLoaders.get(id);
		
//		ResourceLoaderProvider resourceLoaderProvider = resourceLoaderProviders.get(clazz);
//		ResourceLoader resourceLoader = resourceLoaderProvider.get(id);
		
		// call dispose if the resource has custom dispose logic
		resourceLoader.dispose(resource.get());
		
		resource.set(resourceLoader.load());
		
		// for each dependent resource, reload it... without order for now 
	}
	
	/**
	 * Reloads all cached resources.
	 */
	public void reloadAll() {
		for (Object key : cachedResources.keySet())
			reload(key);
	}

	public <T, K extends T> Resource<K> get(Object id, Class<T> clazz, boolean deferred) {
		if (!cachedResources.containsKey(id)) {
			
			if (!resourceLoaders.containsKey(id)) {

				if (!resourceLoaderProviders.containsKey(clazz))
					throw new ResourceLoaderNotRegisteredException("There is no default resource loader for " + clazz.getCanonicalName());
				
				resourceLoaders.put(id, resourceLoaderProviders.get(clazz).get(id));
			}
			
			ResourceLoader resourceLoader = resourceLoaders.get(id);
			
			Resource resource = null;
			
			if (deferred)
				resource = new DeferredResource(resourceLoader);
			else
				resource = new Resource(resourceLoader.load());
			
			if (!resourceLoader.isCached()) {
				return resource;
			}
			
			if (logger.isInfoEnabled())
				logger.info("Caching resource [" + id.toString() + "]");
			
			cachedResources.put(id, resource);
		}
		
		return cachedResources.get(id);
	}
	
}