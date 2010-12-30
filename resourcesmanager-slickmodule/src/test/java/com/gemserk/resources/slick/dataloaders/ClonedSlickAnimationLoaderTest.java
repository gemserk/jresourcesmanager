package com.gemserk.resources.slick.dataloaders;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.newdawn.slick.Animation;

import com.gemserk.resources.dataloaders.DataLoader;

public class ClonedSlickAnimationLoaderTest {

	@Test
	public void shouldReturnAnimationFromDataLoaderFirstTime() {
		Animation expectedAnimation = new Animation();

		DataLoader<Animation> animationDataLoader = createMock(DataLoader.class);
		expect(animationDataLoader.load()).andReturn(expectedAnimation);
		replay(animationDataLoader);
		
		ClonedSlickAnimationLoader clonedSlickAnimationLoader = new ClonedSlickAnimationLoader(animationDataLoader);
		Animation actualAnimation = clonedSlickAnimationLoader.load();
		
		assertSame(expectedAnimation, actualAnimation);
		
		verify(animationDataLoader);
	}
	
	@Test
	public void shouldReturnAnimationCloneFromDataLoaderSecondTime() {
		Animation mockAnimation = createMock(Animation.class);
		Animation expectedAnimation = new Animation();

		DataLoader<Animation> animationDataLoader = createMock(DataLoader.class);
		expect(animationDataLoader.load()).andReturn(mockAnimation);
		expect(mockAnimation.copy()).andReturn(expectedAnimation);
		replay(animationDataLoader, mockAnimation);
		
		ClonedSlickAnimationLoader clonedSlickAnimationLoader = new ClonedSlickAnimationLoader(animationDataLoader);
		clonedSlickAnimationLoader.load(); // first time
		Animation actualAnimation = clonedSlickAnimationLoader.load();
		
		assertSame(expectedAnimation, actualAnimation);
		
		verify(animationDataLoader, mockAnimation);
	}

}
