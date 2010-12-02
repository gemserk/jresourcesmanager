package com.gemserk.resources.slick;

import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManagerOldImpl;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.slick.dataloaders.AnimationLoader;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;
import com.gemserk.resources.slick.dataloaders.SpriteSheetLoader;
import com.google.inject.Inject;

public class PropertiesAnimationLoader {

	ResourceManagerOldImpl resourceManagerOldImpl;

	@Inject
	public void setResourceManager(ResourceManagerOldImpl resourceManagerOldImpl) {
		this.resourceManagerOldImpl = resourceManagerOldImpl;
	}

	public void load(String animationPropertiesFile) {
		try {
			java.util.Properties animationProperties = new java.util.Properties();
			InputStream animationInputStream = new ClassPathDataSource(animationPropertiesFile).getInputStream();
			animationProperties.load(animationInputStream);

			for (Object keyObj : animationProperties.keySet()) {
				String key = (String) keyObj;
				String value = animationProperties.getProperty(key);

				String[] values = value.split(",");
				String file = values[0];
				int width = Integer.parseInt(values[1]);
				int height = Integer.parseInt(values[2]);
				final int time = Integer.parseInt(values[3]);
				final int framesCount = Integer.parseInt(values[4]);

				resourceManagerOldImpl.registerDataLoader(key + "_spriteSheetImage", new SlickImageLoader(file));

				Resource<Image> spriteSheetImageResource = resourceManagerOldImpl.get(key + "_spriteSheetImage", true);
				resourceManagerOldImpl.registerDataLoader(key + "_spriteSheet", new SpriteSheetLoader(spriteSheetImageResource, width, height));
				
				Resource<SpriteSheet> spriteSheetResource = resourceManagerOldImpl.get(key + "_spriteSheet", true);
				resourceManagerOldImpl.registerDataLoader(key, new AnimationLoader(spriteSheetResource, time, framesCount, false), false);
			}

		} catch (IOException e) {
			throw new RuntimeException("failed to load animations from " + animationPropertiesFile, e);
		}
	}

}