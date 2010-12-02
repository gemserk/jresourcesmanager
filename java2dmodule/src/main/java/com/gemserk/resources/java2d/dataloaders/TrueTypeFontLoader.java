package com.gemserk.resources.java2d.dataloaders;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class TrueTypeFontLoader extends DataLoader<Font> {

	private final DataSource dataSource;

	public TrueTypeFontLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Font load() {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, dataSource.getInputStream());
		} catch (FontFormatException e) {
			throw new RuntimeException("File " + dataSource.getResourceName() + " is not a supported font file.", e);
		} catch (IOException e) {
			throw new RuntimeException("Failed to retrieve resource for font " + dataSource.getResourceName(), e);
		}
	}
}