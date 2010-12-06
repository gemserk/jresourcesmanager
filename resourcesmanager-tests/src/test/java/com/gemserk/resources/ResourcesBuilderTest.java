package com.gemserk.resources;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoader;

public class ResourcesBuilderTest {

	@Test
	public void shouldRegisterAResourceLoaderForAnImage() {
		final String id = "CompanyLogo";
		final String file = "assets/images/CompanyLogo.png";

		ResourceManager resourceManager = new ResourceManager() {

			@Override
			public void add(Object id, ResourceLoader resourceLoader) {
				assertTrue(resourceLoader instanceof CachedResourceLoader);
			}

			@Override
			public Resource get(Object id) {
				return null;
			}
		};

		// ResourceManager resourceManager = createMock(ResourceManager.class);
		// resourceManager.add(eq(id), (ResourceLoader) anyObject());
		// replay(resourceManager);

		ResourcesBuilder resourcesBuilder = new ResourcesBuilder(resourceManager) {{
			java2d.image(id, file);
		}};


		// verify(resourceManager);
	}

}
