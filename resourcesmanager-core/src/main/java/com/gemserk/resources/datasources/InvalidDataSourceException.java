/**
 * 
 */
package com.gemserk.resources.datasources;

public class InvalidDataSourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDataSourceException() {
		super();
	}

	public InvalidDataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataSourceException(String message) {
		super(message);
	}

	public InvalidDataSourceException(Throwable cause) {
		super(cause);
	}
	
}