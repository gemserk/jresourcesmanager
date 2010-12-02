package com.gemserk.resources.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.awt.Image;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.gemserk.resources.DataLoaderProvider;
import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoaderNotRegisteredException;
import com.gemserk.resources.ResourceManagerOld;
import com.gemserk.resources.ResourceManagerOldImpl;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.java2d.dataloaders.ImageLoader;


public class ResourcesTest {

	// Features of the ResourceManager
	// * Provide several sources for the resources, class path, file system, url, etc.
	// * Resource load time post processing to generate custom resources (example: transparent image by applying an alpha mask to an image)
	// * Runtime resource transparent reload by using the Resource<T> class.
	// * Resources some times depend on other resources, like post processing resources...., have to reload those when depending resources are reloaded.

	// * Resources loaded at register time or at request time.. lazy, eager.. => load at start time what you need...

	/**
	 * An example of a ResourceLoaderProvider which uses integer as resource id.
	 */
	public static class AwtImageProvider2 extends DataLoaderProvider<Integer, Image> {

		public static int BLACK_LOGO = 0;

		public static int WHITE_LOGO = 1;

		String[] files = { "logo-gemserk-512x116.png", "logo-gemserk-512x116-white.png" };

		@Override
		public DataLoader<Image> get(Integer id) {
			String file = files[id];
			return new ImageLoader(new ClassPathDataSource(file));
		}

	}

	@Test(expected = ResourceLoaderNotRegisteredException.class)
	public void test() {
		ResourceManagerOldImpl resourceManagerOldImpl = new ResourceManagerOldImpl();
		resourceManagerOldImpl.get("logo-gemserk-512x116.png");
	}

	public void registerImageProviders(List<String> files, ResourceManagerOld resourceManagerImpl) {
		for (String file : files)
			resourceManagerImpl.registerDataLoader(file, new ImageLoader(new ClassPathDataSource(file)));
	}
	
	public void registerImageProviders(Map<String, String> files, ResourceManagerOld resourceManagerImpl) {
		for (String file : files.keySet())
			resourceManagerImpl.registerDataLoader(file, new ImageLoader(new ClassPathDataSource(files.get(file))));
	}

	@Test
	public void test2() {
		final ResourceManagerOldImpl resourceManagerOldImpl = new ResourceManagerOldImpl();
		
		registerImageProviders(Arrays.asList("logo-gemserk-512x116.png"), resourceManagerOldImpl);
		
//		resourceManager.registerLoaderProvider(Image.class, new ImageLoaderProvider(Arrays.asList("logo-gemserk-512x116.png")));
		
		Resource<Image> imageResource = resourceManagerOldImpl.get("logo-gemserk-512x116.png");
		assertNotNull(imageResource);
		assertNotNull(imageResource.get());
	}

	// @Test
	// public void test4() {
	// final ResourceManagerImpl resourceManagerImpl = new ResourceManagerImpl();
	// ImageLoaderProvider imageLoaderProvider = new ImageLoaderProvider();
	// resourceManagerImpl.registerResourceLoader("logo-gemserk-512x116.png-inverted", new DataLoader<Image>() {
	// @Override
	// public Image load() {
	// Resource<Image> logoImageResource = resourceManagerImpl.get("logo-gemserk-512x116-white.png");
	// Image data = logoImageResource.get();
	// // do stuff over the data....
	// return data;
	// }
	// });
	//
	// resourceManagerImpl.registerLoaderProvider(Image.class, imageLoaderProvider);
	// Resource<Image> imageResource = resourceManagerImpl.get("logo-gemserk-512x116.png-inverted");
	// assertNotNull(imageResource);
	// assertNotNull(imageResource.get());
	// }
	//
	// @Test
	// public void test5() {
	// final ResourceManagerImpl resourceManagerImpl = new ResourceManagerImpl();
	// resourceManagerImpl.registerLoaderProvider(Font.class, new FontLoaderProvider());
	// Resource<Font> fontResource = resourceManagerImpl.get("arial,0,24");
	// assertNotNull(fontResource);
	// assertNotNull(fontResource.get());
	// }
	//
	// @Test
	// public void test3() {
	// ResourceManagerImpl resourceManagerImpl = new ResourceManagerImpl();
	// resourceManagerImpl.registerLoaderProvider(Image.class, new AwtImageProvider2());
	// Resource<Image> imageResource = resourceManagerImpl.get(1);
	// assertNotNull(imageResource);
	// assertNotNull(imageResource.get());
	// }
	//
	// @Test
	// public void testReloadResource() {
	// final ResourceManagerImpl resourceManagerImpl = new ResourceManagerImpl();
	// resourceManagerImpl.registerLoaderProvider(Font.class, new FontLoaderProvider());
	// Resource<Font> fontResource = resourceManagerImpl.get("arial,0,24");
	// assertNotNull(fontResource);
	// assertNotNull(fontResource.get());
	//
	// Font currentFont = fontResource.get();
	//
	// resourceManagerImpl.reload("arial,0,24");
	//
	// assertNotNull(fontResource);
	// assertNotNull(fontResource.get());
	// assertNotSame(fontResource.get(), currentFont);
	// }

	@Test
	public void testResourcesNotCacheables() {
		final ResourceManagerOldImpl resourceManagerOldImpl = new ResourceManagerOldImpl();

		resourceManagerOldImpl.registerDataLoader("blue-ball", new DataLoader<Animation>() {
			@Override
			public Animation load() {
				return new Animation(false);
			}
		}, false);

		Resource<Animation> blueBallResource1 = resourceManagerOldImpl.get("blue-ball");
		Resource<Animation> blueBallResource2 = resourceManagerOldImpl.get("blue-ball");

		assertNotSame(blueBallResource1.get(), blueBallResource2.get());

	}

}
