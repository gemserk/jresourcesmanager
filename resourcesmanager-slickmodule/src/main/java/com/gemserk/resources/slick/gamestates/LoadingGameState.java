package com.gemserk.resources.slick.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.gemserk.resources.Resource;

@SuppressWarnings("unchecked")
public class LoadingGameState extends BasicGameState {

	private final int id;

	private Resource<Image> logoResource;

	private TaskQueue taskQueue;

	BasicGameState nextGameState;

	Rectangle screenBounds;

	public LoadingGameState(int id, BasicGameState nextGameState, Resource logoResource, TaskQueue taskQueue, Rectangle screenBounds) {
		this.id = id;
		this.nextGameState = nextGameState;
		this.logoResource = logoResource;
		this.taskQueue = taskQueue;
		this.screenBounds = screenBounds;
	}

	@Override
	public int getID() {
		return id;
	}

	protected Progress getProgress() {
		return taskQueue.getProgress();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillRect(0, 0, screenBounds.getWidth(), screenBounds.getHeight());
		g.setColor(Color.white);
		g.drawString("Loading..." + (int) getProgress().getPercentage() + "%", screenBounds.getWidth() / 2 - 40, screenBounds.getHeight() - 60);

		Image image = logoResource.get();
		g.drawImage(image, screenBounds.getWidth() / 2 - image.getWidth() / 2, screenBounds.getHeight() / 2 - image.getHeight() / 2);
	}

	@Override
	public void update(final GameContainer container, final StateBasedGame game, int delta) throws SlickException {

		if (!taskQueue.isDone()) {
			taskQueue.processNext();
			return;
		}

		nextGameState.init(container, game);
		game.addState(nextGameState);

		game.enterState(nextGameState.getID(), new FadeOutTransition(Color.black, 2500), new FadeInTransition(Color.black, 500));
	}

}