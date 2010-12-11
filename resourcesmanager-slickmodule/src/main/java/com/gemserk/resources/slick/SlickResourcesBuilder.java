package com.gemserk.resources.slick;

import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.monitor.FileMonitorResourceHelper;
import com.gemserk.resources.monitor.FileMonitorResourceHelperNullImpl;
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

	private FileMonitorResourceHelper fileMonitorResourceHelper;

	public SlickResourcesBuilder(ResourceManager resourceManager) {
		this(resourceManager, new FileMonitorResourceHelperNullImpl());
	}

	public SlickResourcesBuilder(ResourceManager resourceManager, FileMonitorResourceHelper fileMonitorResourceHelper) {
		this.resourceManager = resourceManager;
		this.fileMonitorResourceHelper = fileMonitorResourceHelper;
	}

	/**
	 * Load all images from a properties file.
	 */
	public void images(String propertiesFile) {
		PropertiesImageLoader propertiesLoader = new PropertiesImageLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.setFileMonitorResourceHelper(fileMonitorResourceHelper);
		propertiesLoader.load(propertiesFile);
	}

	/**
	 * Load all animations from a properties file.
	 */
	public void animations(String propertiesFile) {
		PropertiesAnimationLoader propertiesLoader = new PropertiesAnimationLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.setFileMonitorResourceHelper(fileMonitorResourceHelper);
		propertiesLoader.load(propertiesFile);
	}

	/**
	 * Load all sounds from a properties file.
	 */
	public void sounds(String propertiesFile) {
		PropertiesSoundLoader propertiesLoader = new PropertiesSoundLoader();
		propertiesLoader.setResourceManager(resourceManager);
		propertiesLoader.setFileMonitorResourceHelper(fileMonitorResourceHelper);
		propertiesLoader.load(propertiesFile);
	}

	private void classpathMonitor(String id, String file) {
		fileMonitorResourceHelper.monitorClassPathFile(file, resourceManager.get(id));
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