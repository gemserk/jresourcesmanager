package com.gemserk.resources.java2d.dataloaders;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class TrueTypeFontLoader extends DataLoader<Font> {

	private final DataSource dataSource;

	private final int style;

	private final int size;

	public TrueTypeFontLoader(DataSource dataSource) {
		this(dataSource, Font.PLAIN, 9);
	}

	public TrueTypeFontLoader(DataSource dataSource, int style, int size) {
		this.dataSource = dataSource;
		this.style = style;
		this.size = size;
	}

	@Override
	public Font load() {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, dataSource.getInputStream()).deriveFont((float) size).deriveFont(style);
		} catch (FontFormatException e) {
			throw new RuntimeException("File " + dataSource.getResourceName() + " is not a supported font file.", e);
		} catch (IOException e) {
			throw new RuntimeException("Failed to retrieve resource for font " + dataSource.getResourceName(), e);
		}
	}
}