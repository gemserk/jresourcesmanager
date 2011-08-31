package com.gemserk.resources;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsSame;
import org.junit.Test;

import com.gemserk.resources.dataloaders.DataLoader;


public class VolatileResourceTest {
	
	@Test
	public void shouldReturnNewDataEachTime() {
		
		DataLoader<String> dataLoader = new DataLoader<String>() {
			@Override
			public String load() {
				return new String("A");
			}
		};
		
		VolatileResource<String> volatileResource = new VolatileResource<String>(dataLoader);
		
		String dataA = volatileResource.get();
		String dataB = volatileResource.get();
		
		assertThat(dataA, IsNot.not(IsSame.sameInstance(dataB)));
		
	}

}
