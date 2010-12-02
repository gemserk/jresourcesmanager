package com.gemserk.resources;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.dataloaders.DataLoader;

@SuppressWarnings("unchecked")
public class ResourceManagerOldImpl implements ResourceManagerOld {
	
	protected static final Logger logger = LoggerFactory.getLogger(ResourceManagerOldImpl.class);
	
	Map<Object, Resource> cachedResources = new HashMap<Object, Resource>();
	
	Map<Object, DataLoader> dataLoaders = new HashMap<Object, DataLoader>();

	public void registerDataLoader(Object id, DataLoader dataLoader) {
		registerDataLoader(id, dataLoader, true);
	}
	
	public void registerDataLoader(Object id, DataLoader dataLoader, boolean cached) {
		dataLoaders.put(id, dataLoader);
		dataLoader.setCached(cached);
	}
	
	public <T, K extends T> Resource<K> get(Object id) {
		return get(id, false);
	}

	public void reload(Object id) {
		if (!cachedResources.containsKey(id))  {
			if (logger.isInfoEnabled())
				logger.info("Resource with id " + id.toString() + " not found, maybe is not a cached resource.");
			return;
			// throw new RuntimeException("Resource with id " + id.toString() + " not found, maybe is not a cached resource.");
		}

		Resource resource = cachedResources.get(id);
		
		DataLoader dataLoader = dataLoaders.get(id);
		dataLoader.dispose(resource.get());
		
		resource.set(dataLoader.load());
		
		// for each dependent resource, reload it... without order for now 
	}
	
	public void reloadAll() {
		for (Object key : cachedResources.keySet())
			reload(key);
	}

	public <T, K extends T> Resource<K> get(Object id, boolean deferred) {
		if (!cachedResources.containsKey(id)) {
			
			if (!dataLoaders.containsKey(id)) 
				throw new ResourceLoaderNotRegisteredException("There is no data loader for " + id);
			
			DataLoader dataLoader = dataLoaders.get(id);
			
			Resource resource = null;
			
			if (deferred)
				resource = new DeferredResource(dataLoader);
			else
				resource = new Resource(dataLoader.load());
			
			if (!dataLoader.isCached()) {
				return resource;
			}
			
			if (logger.isInfoEnabled())
				logger.info("Caching resource [" + id.toString() + "]");
			
			cachedResources.put(id, resource);
		}
		
		return cachedResources.get(id);
	}

}