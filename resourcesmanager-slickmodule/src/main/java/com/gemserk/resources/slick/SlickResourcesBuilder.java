package com.gemserk.resources.slick;


import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.monitor.FileUtils;
import com.gemserk.resources.monitor.FilesMonitor;
import com.gemserk.resources.monitor.FilesMonitorNullImpl;
import com.gemserk.resources.monitor.handlers.ReloadResourceWhenFileModified;
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

	private FilesMonitor filesMonitor;

	public SlickResourcesBuilder(ResourceManager resourceManager) {
		this(resourceManager, new FilesMonitorNullImpl());
	}

	public SlickResourcesBuilder(ResourceManager resourceManager, FilesMonitor filesMonitor) {
		this.resourceManager = resourceManager;
		this.filesMonitor = filesMonitor;
	}

	/**
	 * Load all images from a properties file.
	 */
	public void images(String propertiesFile) {
		PropertiesImageLoader propertiesLoader = new PropertiesImageLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.setFilesMonitor(filesMonitor);
		propertiesLoader.load(propertiesFile);
	}

	/**
	 * Load all animations from a properties file.
	 */
	public void animations(String propertiesFile) {
		PropertiesAnimationLoader propertiesLoader = new PropertiesAnimationLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.setFilesMonitor(filesMonitor);
		propertiesLoader.load(propertiesFile);
	}

	/**
	 * Load all sounds from a properties file.
	 */
	public void sounds(String propertiesFile) {
		PropertiesSoundLoader propertiesLoader = new PropertiesSoundLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.setFilesMonitor(filesMonitor);
		propertiesLoader.load(propertiesFile);
	}

	private void classpathMonitor(String id, String file) {
		filesMonitor.monitor(FileUtils.classPathFile(file), new ReloadResourceWhenFileModified(resourceManager.get(id)));
	}

	public void image(String id, String file) {
		DataLoader dataLoader = new SlickImageLoader(file);
		addCachedResourceLoader(id, dataLoader);
		classpathMonitor(id, file);
	}

	public void animation(String id, String file, int th, int tw, int time, int totalFrames, boolean autoUpdate) {
		// animations resources are not cached, we want new resource each time...
		DataLoader dataLoader = new SlickAnimationLoader(file, tw, th, time, totalFrames, autoUpdate);
		addResourceLoader(id, dataLoader);
		classpathMonitor(id, file);
	}

	public void sound(String id, String file) {
		DataLoader dataLoader = new SlickSoundLoader(file);
		addCachedResourceLoader(id, dataLoader);
		classpathMonitor(id, file);
	}

	public void truetypefont(String id, String file, int style, int size) {
		DataLoader dataLoader = new SlickTrueTypeFontLoader(file, style, size);
		addCachedResourceLoader(id, dataLoader);
		classpathMonitor(id, file);
	}

	public void angelcodefont(String id, String fntFile, String imgFile) {
		DataLoader dataLoader = new SlickAngelCodeFontLoader(fntFile, imgFile);
		addCachedResourceLoader(id, dataLoader);
		classpathMonitor(id, fntFile);
		classpathMonitor(id, imgFile);
	}

	private void addCachedResourceLoader(String id, DataLoader dataLoader) {
		resourceManager.add(id, new CachedResourceLoader(new ResourceLoaderImpl(dataLoader)));
	}

	private void addResourceLoader(String id, DataLoader dataLoader) {
		resourceManager.add(id, new ResourceLoaderImpl(dataLoader));
	}

}