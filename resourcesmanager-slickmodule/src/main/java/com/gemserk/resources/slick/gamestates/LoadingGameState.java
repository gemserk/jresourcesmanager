package com.gemserk.resources.slick.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingGameState extends BasicGameState {

	private final int id;

	private TaskQueue taskQueue;

	private final Image image;

	public LoadingGameState(int id, Image image, TaskQueue taskQueue) {
		this.id = id;
		this.image = image;
		this.taskQueue = taskQueue;
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
		float width = container.getWidth();
		float height = container.getHeight();

		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		g.drawString("Loading..." + (int) getProgress().getPercentage() + "%", width / 2 - 40, height - 60);

		g.drawImage(image, width / 2 - image.getWidth() / 2, height / 2 - image.getHeight() / 2);
	}

	@Override
	public void update(final GameContainer container, final StateBasedGame game, int delta) throws SlickException {

		if (!taskQueue.isDone()) {
			taskQueue.processNext();
			return;
		}

	}

}