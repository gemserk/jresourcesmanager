package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.ReloadableResource;

public interface ReloadableResourceLoader<T> {

	ReloadableResource<T> load();

}