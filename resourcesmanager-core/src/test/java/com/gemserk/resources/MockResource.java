package com.gemserk.resources;

import com.gemserk.resources.dataloaders.DataLoader;

public class MockResource<T> extends Resource<T> {

	boolean unloadCalled = false;

	public MockResource(DataLoader dataLoader) {
		super(dataLoader);
	}

	@Override
	public void unload() {
		super.unload();
		unloadCalled = true;
	}

}