package com.gemserk.resources.java2d.resourceloaders;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.streams.ResourceStream;

public class TrueTypeFontLoader extends ResourceLoader<Font> {

	private final ResourceStream resourceStream;

	public TrueTypeFontLoader(ResourceStream resourceStream) {
		this.resourceStream = resourceStream;
	}

	@Override
	public Font load() {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, resourceStream.getInputStream());
		} catch (FontFormatException e) {
			throw new RuntimeException("File " + resourceStream.getResourceName() + " is not a supported font file.", e);
		} catch (IOException e) {
			throw new RuntimeException("Failed to retrieve resource for font " + resourceStream.getResourceName(), e);
		}
	}
}