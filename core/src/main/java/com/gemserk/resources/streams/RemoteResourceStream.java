package com.gemserk.resources.streams;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteResourceStream extends ResourceStream {
	
	protected static final Logger logger = LoggerFactory.getLogger(RemoteResourceStream.class);

	public RemoteResourceStream(String url) {
		super(url);
	}

	public InputStream getInputStream() {
		try {
			if (logger.isInfoEnabled())
				logger.info("loading image from " + getResourceName());
			return new URL(url).openStream();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			// some other exception ? to let the user do something in this case...
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getResourceName() {
		return getUrl();
	}

}