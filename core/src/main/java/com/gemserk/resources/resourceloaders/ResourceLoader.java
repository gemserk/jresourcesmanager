package com.gemserk.resources.resourceloaders;

import com.gemserk.resources.Resource;

public interface ResourceLoader<T> {

	Resource<T> load();

}