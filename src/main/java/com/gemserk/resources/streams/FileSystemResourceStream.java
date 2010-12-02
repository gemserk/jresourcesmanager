package com.gemserk.resources.streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileSystemResourceStream extends ResourceStream {

	public FileSystemResourceStream(String url) {
		super(url);
	}

	public InputStream getInputStream() {
		try {
			return new FileInputStream(new File(getUrl()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getResourceName() {
		return "file://" + getUrl();
	}

}