package com.gemserk.resources.slick2d.resourceloaders;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceLoader;
import com.gemserk.resources.ResourceManager;

public class AnimationLoader extends ResourceLoader<Animation> {

	private final ResourceManager resourceManager;
	private final int totalFrames;
	private final boolean autoUpdate;

	public AnimationLoader(ResourceManager resourceManager, int totalFrames, boolean autoUpdate) {
		this.resourceManager = resourceManager;
		this.totalFrames = totalFrames;
		this.autoUpdate = autoUpdate;
	}

	@Override
	public Animation load() {
		Resource<SpriteSheet> ballAnimationSpriteSheet = resourceManager.get("BallAnimationSpriteSheet", SpriteSheet.class);
		return createAnimation(ballAnimationSpriteSheet.get(), totalFrames, autoUpdate);
	}

	private Animation createAnimation(final SpriteSheet spriteSheet, int totalFrames, boolean autoUpdate) {
		Animation animation = new Animation();
		animation.setAutoUpdate(autoUpdate);

		int horizontalCant = spriteSheet.getHorizontalCount();
		int verticalCant = spriteSheet.getVerticalCount();

		for (int j = 0; j < verticalCant; j++) {
			for (int i = 0; i < horizontalCant; i++) {
				if (i + j * horizontalCant < totalFrames) {
					animation.addFrame(spriteSheet.getSubImage(i, j), 100);
				}
			}
		}

		return animation;
	}
}