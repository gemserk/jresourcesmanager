package com.gemserk.resources.slick;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;
import com.gemserk.resources.datasources.DataSourceFactory;
import com.gemserk.resources.datasources.DataSourceParser;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickAngelCodeFontLoader;
import com.gemserk.resources.slick.dataloaders.SlickAnimationLoader;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;
import com.gemserk.resources.slick.dataloaders.SlickMusicLoader;
import com.gemserk.resources.slick.dataloaders.SlickSoundLoader;
import com.gemserk.resources.slick.dataloaders.SlickTrueTypeFontLoader;
import com.gemserk.resources.slick.dataloaders.SlickUnicodeFontLoader;

@SuppressWarnings("unchecked")
public class SlickResourcesBuilder {

	private ResourceManager resourceManager;

	private DataSourceParser dataSourceParser = new DataSourceParser();

	public SlickResourcesBuilder(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	/**
	 * Load all images from a properties file.
	 */
	public void images(String propertiesFile) {
		PropertiesImageLoader propertiesLoader = new PropertiesImageLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.load(propertiesFile);
	}

	/**
	 * Load all animations from a properties file.
	 */
	public void animations(String propertiesFile) {
		PropertiesAnimationLoader propertiesLoader = new PropertiesAnimationLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.load(propertiesFile);
	}

	/**
	 * Load all sounds from a properties file.
	 */
	public void sounds(String propertiesFile) {
		PropertiesSoundLoader propertiesLoader = new PropertiesSoundLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.load(propertiesFile);
	}

	public DataLoader<Image> image(String url) {
		return image(dataSourceParser.parse(url));
	}

	public DataLoader<Image> image(DataSource dataSource) {
		return new SlickImageLoader(dataSource);
	}

	public DataLoader<Animation> animation(String url, int th, int tw, int time, int totalFrames, boolean autoUpdate) {
		return animation(dataSourceParser.parse(url), th, tw, time, totalFrames, autoUpdate);
	}

	public DataLoader<Animation> animation(DataSource dataSource, int th, int tw, int time, int totalFrames, boolean autoUpdate) {
		return new SlickAnimationLoader(dataSource, tw, th, time, totalFrames, autoUpdate);
	}

	public DataLoader<Sound> sound(String file) {
		return new SlickSoundLoader(file);
	}

	public DataLoader music(String file) {
		return new SlickMusicLoader(file);
	}

	public DataLoader<Font> trueTypeFont(String url, int style, int size) {
		return trueTypeFont(dataSourceParser.parse(url), style, size);
	}

	public DataLoader<Font> trueTypeFont(DataSource dataSource, int style, int size) {
		return new SlickTrueTypeFontLoader(dataSource, style, size);
	}

	public DataLoader<Font> trueTypeFont(java.awt.Font font) {
		return new SlickTrueTypeFontLoader(font);
	}

	public DataLoader<Font> unicodeFont(String ttfFile, String hieroFile) {
		return new SlickUnicodeFontLoader(ttfFile, hieroFile);
	}

	public DataLoader<Font> angelCodeFont(String fntFile, String imgFile) {
		return angelCodeFont(dataSourceParser.parse(fntFile), dataSourceParser.parse(imgFile));
	}

	public DataLoader<Font> angelCodeFont(DataSource fontDataSource, DataSource imageDataSource) {
		return new SlickAngelCodeFontLoader(fontDataSource, imageDataSource);
	}

	public void resource(String id, ResourceLoader resourceLoader) {
		resourceManager.add(id, resourceLoader);
	}

	public ResourceLoader loader(DataLoader dataLoader) {
		return new ResourceLoaderImpl(dataLoader);
	}

	public ResourceLoader cached(ResourceLoader resourceLoader) {
		return new CachedResourceLoader(resourceLoader);
	}

	public DataSource classpath(String path) {
		return DataSourceFactory.classPathDataSource(path);
	}

	public DataSource filesystem(String path) {
		return DataSourceFactory.fileSystemDataSource(path);
	}

	public DataSource remote(String path) {
		return DataSourceFactory.remoteDataSource(path);
	}

}