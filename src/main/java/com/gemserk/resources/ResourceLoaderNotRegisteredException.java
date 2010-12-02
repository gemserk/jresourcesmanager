package com.gemserk.resources;

public class ResourceLoaderNotRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 5479536498246167194L;

	public ResourceLoaderNotRegisteredException() {
		super();
	}

	public ResourceLoaderNotRegisteredException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceLoaderNotRegisteredException(String message) {
		super(message);
	}

	public ResourceLoaderNotRegisteredException(Throwable cause) {
		super(cause);
	}

}