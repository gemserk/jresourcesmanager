package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Animation;

import com.gemserk.resources.dataloaders.DataLoader;

/**
 * Returns a slick animation copy of a loaded animation.
 */
public class ClonedSlickAnimationLoader extends DataLoader<Animation> {

	private final DataLoader<Animation> dataLoader;
	
	private Animation animation = null;

	public ClonedSlickAnimationLoader(DataLoader<Animation> dataLoader) {
		this.dataLoader = dataLoader;
	}

	@Override
	public Animation load() {
		if (animation != null)
			return animation.copy();
		animation = dataLoader.load();
		return animation;
	}
	
	@Override
	public void unload(Animation t) {
		animation = null;
	}

}