package com.gemserk.resources.slick.resourceloaders;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;

public class SlickTrueTypeFontLoader extends ResourceLoader<Font> {

	private boolean antiAlias;

	FontResolver fontResolver;

	public SlickTrueTypeFontLoader(String file, int style, int size) {
		this(file, style, size, true);
	}

	public SlickTrueTypeFontLoader(String file, int style, int size, boolean antiAlias) {
		fontResolver = new FontResolverFileImpl(file, style, size);
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

	class FontResolverFileImpl implements FontResolver {

		final String file;

		final int style;

		final int size;

		FontResolverFileImpl(String file, int style, int size) {
			this.file = file;
			this.style = style;
			this.size = size;
		}

		@Override
		public java.awt.Font resolve() {
			try {
				java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new ClassPathDataSource(file).getInputStream());
				return font.deriveFont((float) size).deriveFont(style);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}
}