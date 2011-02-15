package com.gemserk.resources.slick.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.gemserk.resources.util.progress.Progress;
import com.gemserk.resources.util.progress.TaskQueue;

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

		String percentage = "" + (int) getProgress().getPercentage() + "% - ";
		String message = percentage + "Loading...";

		if (getProgress().getMessage() != null)
			message = percentage + getProgress().getMessage();

		if (getProgress().getSubProgress() != null && getProgress().getSubProgress().getMessage() != null)
			message = percentage + getProgress().getSubProgress().getMessage();

		int messageWidth = g.getFont().getWidth(message);
		g.drawString(message, width / 2 - messageWidth / 2, height - 60);

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