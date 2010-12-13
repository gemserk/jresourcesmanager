package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class SlickAngelCodeFontLoader extends DataLoader<Font> {

	private final DataSource fontDataSource;

	private final DataSource imageDataSource;

	public SlickAngelCodeFontLoader(DataSource fontDataSource, DataSource imageDataSource) {
		this.fontDataSource = fontDataSource;
		this.imageDataSource = imageDataSource;
	}

	@Override
	public Font load() {
		try {
			return new AngelCodeFont(fontDataSource.getResourceName(), fontDataSource.getInputStream(), imageDataSource.getInputStream());
		} catch (SlickException e) {
			throw new RuntimeException("failed to load angel code font ", e);
		}
	}

}