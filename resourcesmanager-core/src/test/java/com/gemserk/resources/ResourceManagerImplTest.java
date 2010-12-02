package com.gemserk.resources;

import static org.junit.Assert.*;

import java.awt.Font;

import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.StaticDataLoader;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;

public class ResourceManagerImplTest {

	class StringMockResourceLoader implements ResourceLoader<String> {

		private final String string;

		public StringMockResourceLoader(String string) {
			this.string = string;
		}

		@Override
		public Resource<String> load() {
			return new Resource<String>(new StaticDataLoader<String>(string));
		}

	}

	@Test
	public void shouldReturnNullResourceIfResourceNotFound() {
		ResourceManager<String> resourceManagerImpl = new ResourceManagerImpl<String>();
		Resource<String> actualResource = resourceManagerImpl.get("MyCompanyLogo");
		assertNull(actualResource);
	}

	@Test
	public void shouldReturnRegisteredResourceWithId() {
		ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("MyCompanyLogo", new ResourceLoaderImpl<String>(new StaticDataLoader<String>("data")));

		Resource<String> actualResource = resourceManager.get("MyCompanyLogo");
		assertNotNull(actualResource);
		assertEquals("data", actualResource.get());
	}

	@Test
	public void shouldReturnResourceLoadedFromLoader() {
		ResourceLoader<String> resourceLoader = new StringMockResourceLoader("HELLO");
		ResourceManager<String> resourceManagerImpl = new ResourceManagerImpl<String>();
		resourceManagerImpl.add("MyString", resourceLoader);
		Resource<String> actualResource = resourceManagerImpl.get("MyString");
		assertNotNull(actualResource);
		assertEquals("HELLO", actualResource.get());
	}

	@Test
	public void shouldReturnResourceLoadedFromLoader2() {
		ResourceLoader<String> resourceLoader = new CachedResourceLoader<String>(new StringMockResourceLoader("HELLO"));
		ResourceManager<String> resourceManagerImpl = new ResourceManagerImpl<String>();
		resourceManagerImpl.add("MyString", resourceLoader);
		Resource<String> actualResource = resourceManagerImpl.get("MyString");
		Resource<String> actualResource2 = resourceManagerImpl.get("MyString");
		assertSame(actualResource, actualResource2);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void addResourcesAndGetThemWithoutCaching() {
		ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("HelloWorldString", new ResourceLoaderImpl<String>(new StaticDataLoader<String>("HELLO")));
		resourceManager.add("HelloWorldFont", new CachedResourceLoader(new ResourceLoaderImpl(new DataLoader<Font>() {
			@Override
			public Font load() {
				return new Font("Arial", Font.BOLD, 26);
			}
		})));
		
		//....
		
		Resource<String> stringResource = resourceManager.get("HelloWorldString");
		Resource<Font> fontResource1 = resourceManager.get("HelloWorldFont");
		Resource<Font> fontResource2 = resourceManager.get("HelloWorldFont");

		assertNotNull(stringResource);
		assertNotNull(fontResource1);
		assertNotNull(fontResource2);
		
		assertSame(fontResource1, fontResource2);
	}
}
