package com.gemserk.resources.slick;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;
import com.gemserk.resources.datasources.DataSourceFactory;
import com.gemserk.resources.datasources.DataSourceParser;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickAngelCodeFontLoader;
import com.gemserk.resources.slick.dataloaders.SlickAnimationLoader;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;
import com.gemserk.resources.slick.dataloaders.SlickSoundLoader;
import com.gemserk.resources.slick.dataloaders.SlickTrueTypeFontLoader;

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

	public void image(String id, String url) {
		image(id, dataSourceParser.parse(url));
	}

	public void image(String id, DataSource dataSource) {
		DataLoader dataLoader = new SlickImageLoader(dataSource);
		addCachedResourceLoader(id, dataLoader);
	}

	public void animation(String id, String url, int th, int tw, int time, int totalFrames, boolean autoUpdate) {
		animation(id, dataSourceParser.parse(url), th, tw, time, totalFrames, autoUpdate);
	}

	public void animation(String id, DataSource dataSource, int th, int tw, int time, int totalFrames, boolean autoUpdate) {
		// animations resources are not cached, we want new resource each time...
		DataLoader dataLoader = new SlickAnimationLoader(dataSource, tw, th, time, totalFrames, autoUpdate);
		addResourceLoader(id, dataLoader);
	}

	public void sound(String id, String file) {
		DataLoader dataLoader = new SlickSoundLoader(file);
		addCachedResourceLoader(id, dataLoader);
	}

	public void truetypefont(String id, String url, int style, int size) {
		truetypefont(id, dataSourceParser.parse(url), style, size);
	}
	
	public void truetypefont(String id, DataSource dataSource, int style, int size) {
		DataLoader dataLoader = new SlickTrueTypeFontLoader(dataSource, style, size);
		addCachedResourceLoader(id, dataLoader);
	}

	public void angelcodefont(String id, String fntFile, String imgFile) {
		angelcodefont(id, dataSourceParser.parse(fntFile), dataSourceParser.parse(imgFile));
	}

	public void angelcodefont(String id, DataSource fontDataSource, DataSource imageDataSource) {
		DataLoader dataLoader = new SlickAngelCodeFontLoader(fontDataSource, imageDataSource);
		addCachedResourceLoader(id, dataLoader);
	}

	private void addCachedResourceLoader(String id, DataLoader dataLoader) {
		resourceManager.add(id, new CachedResourceLoader(new ResourceLoaderImpl(dataLoader)));
	}

	private void addResourceLoader(String id, DataLoader dataLoader) {
		resourceManager.add(id, new ResourceLoaderImpl(dataLoader));
	}

	public DataSource classPathDataSource(String path) {
		return DataSourceFactory.classPathDataSource(path);
	}

	public DataSource fileSystemDataSource(String path) {
		return DataSourceFactory.fileSystemDataSource(path);
	}

	public DataSource remoteDataSource(String path) {
		return DataSourceFactory.remoteDataSource(path);
	}

}