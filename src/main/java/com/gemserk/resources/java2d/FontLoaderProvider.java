package com.gemserk.resources.java2d;

import java.awt.Font;

import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceLoaderProvider;

public class FontLoaderProvider extends ResourceLoaderProvider<String, Font> {

	public static class FontKey {

		public final String name;

		public final int type;

		public final int size;

		FontKey(String name, int type, int size) {
			this.name = name;
			this.type = type;
			this.size = size;
		}

	}

	@Override
	public ResourceLoader<Font> get(final String id) {
		return new ResourceLoader<Font>() {
			@Override
			public Font load() {
				FontKey fontKey = parseFontDescription(id);
				return new Font(fontKey.name, fontKey.type, fontKey.size);
			}
		};
	}

	protected FontKey parseFontDescription(String fontDescription) {
		String[] values = fontDescription.split(",");
		return new FontKey(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
	}

}