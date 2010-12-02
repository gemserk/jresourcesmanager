package com.gemserk.resources.slick.resourceloaders;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceManager;

public class AnimationLoader extends ResourceLoader<Animation> {

	private final ResourceManager resourceManager;
	private final int totalFrames;
	private final boolean autoUpdate;
	private final Object spriteSheetResource;
	private int time;
	
	public AnimationLoader(ResourceManager resourceManager, Object spriteSheetResource, int time, int totalFrames, boolean autoUpdate) {
		this.resourceManager = resourceManager;
		this.spriteSheetResource = spriteSheetResource;
		this.totalFrames = totalFrames;
		this.autoUpdate = autoUpdate;
		this.time = time;
	}

	@Override
	public Animation load() {
		Resource<SpriteSheet> ballAnimationSpriteSheet = resourceManager.get(spriteSheetResource, SpriteSheet.class);
		return createAnimation(ballAnimationSpriteSheet.get(), totalFrames, time, autoUpdate);
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