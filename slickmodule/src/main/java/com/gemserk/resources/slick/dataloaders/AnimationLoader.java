package com.gemserk.resources.slick.dataloaders;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

import com.gemserk.resources.Resource;
import com.gemserk.resources.dataloaders.DataLoader;

public class AnimationLoader extends DataLoader<Animation> {

	private final int totalFrames;

	private final boolean autoUpdate;

	private int time;

	private Resource<SpriteSheet> spriteSheetResource;

	public AnimationLoader(Resource<SpriteSheet> spriteSheetResource, int time, int totalFrames, boolean autoUpdate) {
		this.spriteSheetResource = spriteSheetResource;
		this.totalFrames = totalFrames;
		this.autoUpdate = autoUpdate;
		this.time = time;
	}

	@Override
	public Animation load() {
		return createAnimation(spriteSheetResource.get(), totalFrames, time, autoUpdate);
	}

	/**
	 * Load an animation specifying total frames from a spritesheet.
	 */
	private Animation createAnimation(SpriteSheet spriteSheet, int totalFrames, int time, boolean autoUpdate) {
		Animation animation = new Animation();
		animation.setAutoUpdate(autoUpdate);

		int horizontalCant = spriteSheet.getHorizontalCount();
		int verticalCant = spriteSheet.getVerticalCount();

		for (int j = 0; j < verticalCant; j++) {
			for (int i = 0; i < horizontalCant; i++) {
				if (i + j * horizontalCant < totalFrames) {
					animation.addFrame(spriteSheet.getSubImage(i, j), time);
				}
			}
		}

		return animation;
	}
}