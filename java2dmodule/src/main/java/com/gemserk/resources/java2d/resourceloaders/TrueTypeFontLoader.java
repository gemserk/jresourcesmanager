package com.gemserk.resources.java2d.resourceloaders;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.datasources.ResourceDataSource;

public class TrueTypeFontLoader extends ResourceLoader<Font> {

	private final ResourceDataSource resourceDataSource;

	public TrueTypeFontLoader(ResourceDataSource resourceDataSource) {
		this.resourceDataSource = resourceDataSource;
	}

	@Override
	public Font load() {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, resourceDataSource.getInputStream());
		} catch (FontFormatException e) {
			throw new RuntimeException("File " + resourceDataSource.getResourceName() + " is not a supported font file.", e);
		} catch (IOException e) {
			throw new RuntimeException("Failed to retrieve resource for font " + resourceDataSource.getResourceName(), e);
		}
	}
}