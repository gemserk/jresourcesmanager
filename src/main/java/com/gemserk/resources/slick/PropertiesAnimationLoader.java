package com.gemserk.resources.slick;

import java.io.IOException;
import java.io.InputStream;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.slick.resourceloaders.AnimationLoader;
import com.gemserk.resources.slick.resourceloaders.SlickImageResourceLoader;
import com.gemserk.resources.slick.resourceloaders.SpriteSheetLoader;
import com.gemserk.resources.streams.ClassPathResourceStream;
import com.google.inject.Inject;

public class PropertiesAnimationLoader {

	ResourceManager resourceManager;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void load(String animationPropertiesFile) {
		try {
			java.util.Properties animationProperties = new java.util.Properties();
			InputStream animationInputStream = new ClassPathResourceStream(animationPropertiesFile).getInputStream();
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

				resourceManager.registerResourceLoader(key + "_spriteSheetImage", new SlickImageResourceLoader(file));
				resourceManager.registerResourceLoader(key + "_spriteSheet", new SpriteSheetLoader(resourceManager, key + "_spriteSheetImage", width, height));
				resourceManager.registerResourceLoader(key, new AnimationLoader(resourceManager, key + "_spriteSheet", time, framesCount, false), false);
			}

		} catch (IOException e) {
			throw new RuntimeException("failed to load animations from " + animationPropertiesFile, e);
		}
	}

}