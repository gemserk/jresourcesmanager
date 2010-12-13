package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class SlickTrueTypeFontLoader extends DataLoader<Font> {

	private boolean antiAlias;

	FontResolver fontResolver;

	public SlickTrueTypeFontLoader(DataSource dataSource, int style, int size) {
		this(dataSource, style, size, true);
	}

	public SlickTrueTypeFontLoader(DataSource dataSource, int style, int size, boolean antiAlias) {
		fontResolver = new FontResolverDataSourceImpl(dataSource, style, size);
		this.antiAlias = antiAlias;
	}
	
	public SlickTrueTypeFontLoader(java.awt.Font awtFont) {
		this(awtFont, true);
	}

	public SlickTrueTypeFontLoader(java.awt.Font awtFont, boolean antiAlias) {
		fontResolver = new FontResolverImpl(awtFont);
		this.antiAlias = antiAlias;
	}

	@Override
	public Font load() {
		return new TrueTypeFont(fontResolver.resolve(), antiAlias);
	}

	interface FontResolver {

		java.awt.Font resolve();

	}

	class FontResolverImpl implements FontResolver {

		final java.awt.Font font;

		FontResolverImpl(java.awt.Font font) {
			this.font = font;
		}

		@Override
		public java.awt.Font resolve() {
			return font;
		}

	}

	class FontResolverDataSourceImpl implements FontResolver {

		final int style;

		final int size;

		final DataSource dataSource;

		FontResolverDataSourceImpl(DataSource dataSource, int style, int size) {
			this.style = style;
			this.size = size;
			this.dataSource = dataSource;
		}

		@Override
		public java.awt.Font resolve() {
			try {
				java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, dataSource.getInputStream());
				return font.deriveFont((float) size).deriveFont(style);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}
}