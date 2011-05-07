package com.gemserk.resources.dataloaders;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CachedDataLoaderTest {

	@Test
	public void shouldAskForNewDataWhenNotCachedData() {
		String expectedData = "data";

		DataLoader dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(expectedData);
		replay(dataLoader);

		CachedDataLoader<String> cachedResourceLoader = new CachedDataLoader<String>(dataLoader);
		String data = cachedResourceLoader.load();
		assertEquals(expectedData, data);

		verify(dataLoader);
	}

	@Test
	public void shouldNotAskForNewDataWhenCachedData() {
		String expectedData = "data";

		DataLoader dataLoader = createMock(DataLoader.class);
		expect(dataLoader.load()).andReturn(expectedData).once();
		replay(dataLoader);

		CachedDataLoader<String> cachedResourceLoader = new CachedDataLoader<String>(dataLoader);
		cachedResourceLoader.load();
		String data = cachedResourceLoader.load();

		assertEquals(expectedData, data);

		verify(dataLoader);
	}

}
