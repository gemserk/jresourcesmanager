package com.gemserk.resources.slick.progress.task;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * @author arielsan
 * A runnable to enter another slick game state when it runs, used for loading game state.
 */
public class EnterNextStateRunnable implements Runnable {

	private final GameContainer container;

	private final StateBasedGame stateBasedGame;

	BasicGameState gameState;

	public EnterNextStateRunnable(GameContainer container, StateBasedGame stateBasedGame, BasicGameState gameState) {
		this.container = container;
		this.stateBasedGame = stateBasedGame;
		this.gameState = gameState;
	}

	@Override
	public void run() {
		try {
			stateBasedGame.addState(gameState);
			gameState.init(container, stateBasedGame);
			stateBasedGame.enterState(gameState.getID(), new FadeOutTransition(Color.black, 1500), new FadeInTransition(Color.black, 1000));
		} catch (SlickException e) {
			throw new RuntimeException(e);
		}
	}
}