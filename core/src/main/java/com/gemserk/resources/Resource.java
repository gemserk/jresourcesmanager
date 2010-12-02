package com.gemserk.resources;

public class Resource<T> {
	
	private T data;
	
	public Resource(T data) {
		this.data = data;
	}
	
	public void set(T data) {
		this.data = data;
	}
	
	public T get() {
		return data;
	}

}
