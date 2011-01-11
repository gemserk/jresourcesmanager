package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;

public class SlickImageLoader extends DataLoader<Image> {

	private final DataSource dataSource;

	public SlickImageLoader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Image load() {
		try {
			return new Image(dataSource.getInputStream(), dataSource.getResourceName(), false);
		} catch (SlickException e) {
			throw new RuntimeException("Failed to load slick image from file " + dataSource.getResourceName(), e);
		}
	}

	@Override
	public void dispose(Image image) {
		try {
			image.destroy();
		} catch (SlickException e) {
			throw new RuntimeException(e);
		}
	}
}