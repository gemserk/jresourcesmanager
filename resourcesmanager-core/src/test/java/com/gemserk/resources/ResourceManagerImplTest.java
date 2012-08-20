package com.gemserk.resources;

import static org.junit.Assert.*;

import java.awt.Font;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.dataloaders.MockDataLoader;
import com.gemserk.resources.dataloaders.StaticDataLoader;

public class ResourceManagerImplTest {

	@Test
	public void shouldReturnNullResourceIfResourceNotFound() {
		ResourceManager<String> resourceManagerImpl = new ResourceManagerImpl<String>();
		Resource<String> actualResource = resourceManagerImpl.get("MyCompanyLogo");
		assertNull(actualResource);
	}

	@Test
	public void shouldReturnRegisteredResourceWithId() {
		ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("MyCompanyLogo", new StaticDataLoader<String>("data"));

		Resource<String> actualResource = resourceManager.get("MyCompanyLogo");
		assertNotNull(actualResource);
		assertEquals("data", actualResource.get());
	}

	@Test
	public void shouldReturnResourceLoadedFromLoader() {
		ResourceManager<String> resourceManagerImpl = new ResourceManagerImpl<String>();
		resourceManagerImpl.add("MyString", new StaticDataLoader<String>("HELLO"));
		Resource<String> actualResource = resourceManagerImpl.get("MyString");
		assertNotNull(actualResource);
		assertEquals("HELLO", actualResource.get());
	}

	@Test
	public void shouldReturnResourceLoadedFromLoader2() {
		// ResourceLoader<String> resourceLoader = new CachedResourceLoader<String>(new StringMockResourceLoader("HELLO"));
		ResourceManager<String> resourceManagerImpl = new ResourceManagerImpl<String>();
		resourceManagerImpl.add("MyString", new StaticDataLoader<String>("HELLO"));
		Resource<String> actualResource = resourceManagerImpl.get("MyString");
		Resource<String> actualResource2 = resourceManagerImpl.get("MyString");
		assertSame(actualResource, actualResource2);
	}

	@Test
	public void addResourcesAndGetThemWithoutCaching() {
		ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("HelloWorldString", new StaticDataLoader<String>("HELLO"));
		resourceManager.add("HelloWorldFont", new DataLoader<Font>() {
			@Override
			public Font load() {
				return new Font("Arial", Font.BOLD, 26);
			}
		});

		// ....

		Resource<String> stringResource = resourceManager.get("HelloWorldString");
		Resource<Font> fontResource1 = resourceManager.get("HelloWorldFont");
		Resource<Font> fontResource2 = resourceManager.get("HelloWorldFont");

		assertNotNull(stringResource);
		assertNotNull(fontResource1);
		assertNotNull(fontResource2);

		assertSame(fontResource1, fontResource2);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void shouldCallUnloadToResourcesWhenUnloadAllCalled() {

		ResourceManager<String> resourceManagerImpl = new ResourceManagerImpl<String>();

		MockDataLoader dataLoader = new MockDataLoader("A");
		
		resourceManagerImpl.add("MyString", dataLoader);

		Resource<String> actualResource = resourceManagerImpl.get("MyString");
		
		actualResource.get();
		
		assertThat(dataLoader.loaded, IsEqual.equalTo(true));
		
		resourceManagerImpl.unloadAll();

		assertThat(dataLoader.loaded, IsEqual.equalTo(false));

	}

	@Test
	public void shouldReturnResourceValue() {
		ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

		resourceManager.add("MyString", new MockDataLoader<String>("VALUE"));

		String resourceValue = resourceManager.getResourceValue("MyString");
		assertThat(resourceValue, IsNull.notNullValue());
		assertThat(resourceValue, IsEqual.equalTo("VALUE"));
	}

	@Test
	public void shouldReturnNullIfResourceForValueDoesntExist() {
		ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();
		String resourceValue = resourceManager.getResourceValue("NULL");
		assertThat(resourceValue, IsNull.nullValue());
	}

	@Test
	public void shouldReturnCachedResource() {

		ResourceManagerImpl<String> resourceManager = new ResourceManagerImpl<String>();
		
		resourceManager.add("A", new MockDataLoader<String>("data1"));
		
		Resource<Object> resourceA = resourceManager.get("A");
		Resource<Object> resourceB = resourceManager.get("A");
		
		assertThat(resourceA, IsSame.sameInstance(resourceB));

	}
	
	@Test
	public void shouldUnloadResources() {
		ResourceManagerImpl<String> resourceManager = new ResourceManagerImpl<String>();
		
		resourceManager.add("A", new MockDataLoader<String>("data1"));
		
		Resource<String> resource = resourceManager.get("A");
		resource.load();
		
		assertThat(resource.isLoaded(), IsEqual.equalTo(true));

		resourceManager.unloadAll();
		
		assertThat(resource.isLoaded(), IsEqual.equalTo(false));
	}
	
	// and should not track it...
	
	@Test
	public void shouldAddAndReturnVolatileResource() {
		ResourceManagerImpl<String> resourceManager = new ResourceManagerImpl<String>();
		
		resourceManager.addVolatile("A", new MockDataLoader<String>("data1"));
		
		Resource<String> volatileResource1 = resourceManager.get("A");
		
		assertThat(volatileResource1, IsNull.notNullValue());
	}
	
	@Test
	public void shouldReturnDifferentResourceEachTimeIfVolatile() {
		ResourceManagerImpl<String> resourceManager = new ResourceManagerImpl<String>();
		
		resourceManager.addVolatile("A", new MockDataLoader<String>("data1"));
		
		Resource<String> volatileResource1 = resourceManager.get("A");
		Resource<String> volatileResource2 = resourceManager.get("A");
		
		assertThat(volatileResource1, IsNot.not(IsSame.sameInstance(volatileResource2)));
	}
	
	@Test
	public void shouldNotUnloadAVolatileResoruce() {
		ResourceManagerImpl<String> resourceManager = new ResourceManagerImpl<String>();
		
		resourceManager.addVolatile("A", new MockDataLoader<String>("data1"));
		
		Resource<String> volatileResource1 = resourceManager.get("A");
		volatileResource1.load();
		
		assertThat(volatileResource1.isLoaded(), IsEqual.equalTo(true));

		resourceManager.unloadAll();
		
		assertThat(volatileResource1.isLoaded(), IsEqual.equalTo(true));
	}

}
