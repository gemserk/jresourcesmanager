package com.gemserk.resources.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.awt.Font;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceLoaderNotRegisteredException;
import com.gemserk.resources.ResourceLoaderProvider;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.java2d.FontLoaderProvider;
import com.gemserk.resources.java2d.ImageLoaderProvider;
import com.gemserk.resources.java2d.resourceloaders.ImageResourceLoader;
import com.gemserk.resources.streams.ClassPathResourceStream;


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
	public static class AwtImageProvider2 extends ResourceLoaderProvider<Integer, Image> {

		public static int BLACK_LOGO = 0;

		public static int WHITE_LOGO = 1;

		String[] files = { "logo-gemserk-512x116.png", "logo-gemserk-512x116-white.png" };

		@Override
		public ResourceLoader<Image> get(Integer id) {
			String file = files[id];
			return new ImageResourceLoader(new ClassPathResourceStream(file));
		}

	}

	@Test(expected = ResourceLoaderNotRegisteredException.class)
	public void test() {
		ResourceManager resourceManager = new ResourceManager();
		resourceManager.get("logo-gemserk-512x116.png", Image.class);
	}

	public void registerImageProviders(List<String> files, ResourceManager resourceManager) {
		for (String file : files)
			resourceManager.registerResourceLoader(file, new ImageResourceLoader(new ClassPathResourceStream(file)));
	}
	
	public void registerImageProviders(Map<String, String> files, ResourceManager resourceManager) {
		for (String file : files.keySet())
			resourceManager.registerResourceLoader(file, new ImageResourceLoader(new ClassPathResourceStream(files.get(file))));
	}

	@Test
	public void test2() {
		final ResourceManager resourceManager = new ResourceManager();
		
		registerImageProviders(Arrays.asList("logo-gemserk-512x116.png"), resourceManager);
		
//		resourceManager.registerLoaderProvider(Image.class, new ImageLoaderProvider(Arrays.asList("logo-gemserk-512x116.png")));
		
		Resource<Image> imageResource = resourceManager.get("logo-gemserk-512x116.png", Image.class);
		assertNotNull(imageResource);
		assertNotNull(imageResource.get());
	}

	@Test
	public void test4() {
		final ResourceManager resourceManager = new ResourceManager();
		ImageLoaderProvider imageLoaderProvider = new ImageLoaderProvider();
		resourceManager.registerResourceLoader("logo-gemserk-512x116.png-inverted", new ResourceLoader<Image>() {
			@Override
			public Image load() {
				Resource<Image> logoImageResource = resourceManager.get("logo-gemserk-512x116-white.png", Image.class);
				Image data = logoImageResource.get();
				// do stuff over the data....
				return data;
			}
		});

		resourceManager.registerLoaderProvider(Image.class, imageLoaderProvider);
		Resource<Image> imageResource = resourceManager.get("logo-gemserk-512x116.png-inverted", Image.class);
		assertNotNull(imageResource);
		assertNotNull(imageResource.get());
	}

	@Test
	public void test5() {
		final ResourceManager resourceManager = new ResourceManager();
		resourceManager.registerLoaderProvider(Font.class, new FontLoaderProvider());
		Resource<Font> fontResource = resourceManager.get("arial,0,24", Font.class);
		assertNotNull(fontResource);
		assertNotNull(fontResource.get());
	}

	@Test
	public void test3() {
		ResourceManager resourceManager = new ResourceManager();
		resourceManager.registerLoaderProvider(Image.class, new AwtImageProvider2());
		Resource<Image> imageResource = resourceManager.get(1, Image.class);
		assertNotNull(imageResource);
		assertNotNull(imageResource.get());
	}

	@Test
	public void testReloadResource() {
		final ResourceManager resourceManager = new ResourceManager();
		resourceManager.registerLoaderProvider(Font.class, new FontLoaderProvider());
		Resource<Font> fontResource = resourceManager.get("arial,0,24", Font.class);
		assertNotNull(fontResource);
		assertNotNull(fontResource.get());

		Font currentFont = fontResource.get();

		resourceManager.reload("arial,0,24");

		assertNotNull(fontResource);
		assertNotNull(fontResource.get());
		assertNotSame(fontResource.get(), currentFont);
	}

	@Test
	public void testResourcesNotCacheables() {
		final ResourceManager resourceManager = new ResourceManager();

		resourceManager.registerResourceLoader("blue-ball", new ResourceLoader<Animation>() {
			@Override
			public Animation load() {
				return new Animation(false);
			}
		}, false);

		Resource<Animation> blueBallResource1 = resourceManager.get("blue-ball", Animation.class);
		Resource<Animation> blueBallResource2 = resourceManager.get("blue-ball", Animation.class);

		assertNotSame(blueBallResource1.get(), blueBallResource2.get());

	}

}
