package com.gemserk.resources.slick;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.classextension.EasyMock.*;

import java.util.Properties;

import org.junit.Test;

import com.gemserk.resources.PropertiesLoader;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.resourceloaders.ResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;

@SuppressWarnings("unchecked")
public class PropertiesConceptTest {

	interface ResourceRegistry {

		void add(String id, ResourceLoader resourceLoader);

	}
	
	class FileMonitorResourceRegistry implements ResourceRegistry {

		@Override
		public void add(String id, ResourceLoader resourceLoader) {
			
		}
		
	}

	interface PropertiesResourcesLoader {

		void load(Properties properties, ResourceRegistry resourceRegistry);

	}

	class SlickImagePropertiesLoader implements PropertiesResourcesLoader {

		@Override
		public void load(Properties properties, ResourceRegistry resourceRegistry) {
			for (String id : properties.stringPropertyNames()) {
				String imageProperties = properties.getProperty(id);
				String[] values = imageProperties.split(",");
				String file = values[0];
				DataLoader dataLoader = new SlickImageLoader(new ClassPathDataSource(file));
				ResourceLoader resourceLoader = new ResourceLoaderImpl(dataLoader);
				resourceRegistry.add(id, resourceLoader);
			}
		}

	}

	@Test
	public void shouldRegisterResourcesFromProperties() {

		ResourceRegistry resourceRegistry = createMock(ResourceRegistry.class);

		Properties properties = new Properties();
		properties.put("image1", "assets/image1.png");

		resourceRegistry.add(eq("image1"), (ResourceLoader) anyObject());

		replay(resourceRegistry);

		PropertiesResourcesLoader propertiesResourcesLoader = new SlickImagePropertiesLoader();
		propertiesResourcesLoader.load(properties, resourceRegistry);

		verify(resourceRegistry);
	}

	class Something {

		PropertiesLoader propertiesLoader;

		ResourceRegistry resourceRegistry;

		PropertiesResourcesLoader propertiesResourcesLoader;

		public Something(PropertiesLoader propertiesLoader, ResourceRegistry resourceRegistry, PropertiesResourcesLoader propertiesResourcesLoader) {
			super();
			this.propertiesLoader = propertiesLoader;
			this.resourceRegistry = resourceRegistry;
			this.propertiesResourcesLoader = propertiesResourcesLoader;
		}

		public void loadFromClasspath(String file) {
			propertiesResourcesLoader.load(propertiesLoader.load(new ClassPathDataSource(file)), resourceRegistry);
		}

	}
	
	@Test
	public void test() {
		
		ResourceRegistry resourceRegistry = createMock(ResourceRegistry.class);
		PropertiesResourcesLoader propertiesResourcesLoader = new SlickImagePropertiesLoader();
		PropertiesLoader propertiesLoader = new PropertiesLoader();
		
		Something something = new Something(propertiesLoader, resourceRegistry, propertiesResourcesLoader);

	}

}
