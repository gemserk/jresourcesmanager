package com.gemserk.resources.slick;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.AnimationLoader;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;
import com.gemserk.resources.slick.dataloaders.SpriteSheetLoader;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class PropertiesAnimationLoader {

	ResourceManager resourceManager;

	@Inject
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	public void load(String animationPropertiesFile) {
		try {
			Properties properties = new Properties();
			InputStream propertiesInputStream = new ClassPathDataSource(animationPropertiesFile).getInputStream();
			properties.load(propertiesInputStream);

			for (Object keyObj : properties.keySet()) {
				String key = (String) keyObj;
				String value = properties.getProperty(key);

				String[] values = value.split(",");
				String file = values[0];
				int width = Integer.parseInt(values[1]);
				int height = Integer.parseInt(values[2]);
				final int time = Integer.parseInt(values[3]);
				final int framesCount = Integer.parseInt(values[4]);

				SlickImageLoader imageLoader = new SlickImageLoader(file);
				ResourceLoader imageResourceLoader = new CachedResourceLoader(new ResourceLoaderImpl(imageLoader));
				// resourceManager.add(key + "_spriteSheetImage", imageResourceLoader);

				SpriteSheetLoader spriteSheetLoader = new SpriteSheetLoader(imageResourceLoader.load(), width, height);
				ResourceLoader spriteSheetResourceLoader = new CachedResourceLoader(new ResourceLoaderImpl(spriteSheetLoader));
				// resourceManager.add(key + "_spriteSheet", spriteSheetResourceLoader);

				AnimationLoader animationLoader = new AnimationLoader(spriteSheetResourceLoader.load(), time, framesCount, false);
				ResourceLoader animationResourceLoader = new ResourceLoaderImpl(animationLoader);
				resourceManager.add(key, animationResourceLoader);
			}

		} catch (IOException e) {
			throw new RuntimeException("failed to load animations from " + animationPropertiesFile, e);
		}
	}

}