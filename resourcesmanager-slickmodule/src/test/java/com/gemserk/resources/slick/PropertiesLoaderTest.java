package com.gemserk.resources.slick;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.DataSource;
import com.gemserk.resources.resourceloaders.ResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;

@SuppressWarnings("unchecked")
public class PropertiesLoaderTest {

	interface ResourceRegistry {

		void add(String id, ResourceLoader resourceLoader);

	}

	class PropertiesLoader {

		Properties load(DataSource dataSource) {
			try {
				Properties properties = new Properties();
				properties.load(dataSource.getInputStream());
				return properties;
			} catch (IOException e) {
				throw new RuntimeException("Failed to load properties from " + dataSource.getResourceName(), e);
			}
		}

	}

	@Test
	public void shouldLoadPropertiesFromDataSource() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test.properties");

		DataSource propertiesDataSource = createMock(DataSource.class);
		expect(propertiesDataSource.getInputStream()).andReturn(inputStream);
		replay(propertiesDataSource);

		PropertiesLoader propertiesLoader = new PropertiesLoader();
		Properties properties = propertiesLoader.load(propertiesDataSource);
		assertNotNull(properties);

		String value1 = (String) properties.get("key1");
		assertEquals("value1", value1);

		verify(propertiesDataSource);
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
				DataLoader dataLoader = new SlickImageLoader(file);
				ResourceLoader resourceLoader = new ResourceLoaderImpl(dataLoader);
				resourceRegistry.add(id, resourceLoader);
			}
		}
		
	}

	@Test
	public void shouldRegisterResourcesFromProperties() {

		ResourceRegistry resourceRegistry = createMock(ResourceRegistry.class);
		Properties properties = new Properties();

		PropertiesResourcesLoader propertiesResourcesLoader = new SlickImagePropertiesLoader();
		propertiesResourcesLoader.load(properties, resourceRegistry);
		

	}

}
