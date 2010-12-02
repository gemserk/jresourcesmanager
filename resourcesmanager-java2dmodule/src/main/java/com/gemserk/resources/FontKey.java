package com.gemserk.resources;

public class FontKey {

	public final String name;

	public final int type;

	public final int size;

	FontKey(String name, int type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
	}

	public static FontKey parseFontDescription(String fontDescription) {
		String[] values = fontDescription.split(",");
		return new FontKey(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
	}
}