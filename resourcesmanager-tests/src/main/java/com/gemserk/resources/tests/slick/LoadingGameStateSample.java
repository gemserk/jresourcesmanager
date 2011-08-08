package com.gemserk.resources.tests.slick;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.progress.TaskQueue;
import com.gemserk.resources.progress.tasks.SimulateLoadingTimeRunnable;
import com.gemserk.resources.resourceloaders.CachedResourceLoader;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;
import com.gemserk.resources.slick.dataloaders.SlickSoundLoader;
import com.gemserk.resources.slick.dataloaders.SlickTrueTypeFontLoader;
import com.gemserk.resources.slick.gamestates.LoadingGameState;
import com.gemserk.resources.slick.gamestates.ResourceManagerLoaderProxyImpl;
import com.gemserk.resources.slick.progress.task.EnterNextStateRunnable;

public class LoadingGameStateSample extends StateBasedGame {

	protected Logger logger = LoggerFactory.getILoggerFactory().getLogger(LoadingGameStateSample.class.getName());

	public static void main(String[] arguments) {

		try {
			LoadingGameStateSample loadingGameStateSample = new LoadingGameStateSample();

			AppGameContainer app = new AppGameContainer(loadingGameStateSample);

			// inicializo todos los subsistemas?

			app.setDisplayMode(640, 480, false);
			app.setAlwaysRender(true);
			app.setShowFPS(false);

			app.start();

		} catch (Exception e) {
			System.exit(0);
		}
	}

	public LoadingGameStateSample() {
		super("Load Game State Sample");
	}

	ResourceManager<String> resourceManager;

	@Override
	public void initStatesList(final GameContainer container) throws SlickException {
		container.setVSync(true);

		TaskQueue taskQueue = new TaskQueue();

		resourceManager = new ResourceManagerLoaderProxyImpl<String>(new ResourceManagerImpl<String>(), taskQueue);

		taskQueue.add(new SimulateLoadingTimeRunnable(200));
		taskQueue.add(new SimulateLoadingTimeRunnable(5000));
		taskQueue.add(new SimulateLoadingTimeRunnable(1000));

		{
			resourceManager.add("BusinessCard", new CachedResourceLoader(new ResourceLoaderImpl<Image>(new SlickImageLoader(new ClassPathDataSource("assets/images/businesscard_front_landscape_ariel_fashionvictim_1.png")))));

			// resourceManager.add("SoundSample", new CachedResourceLoader(new ResourceLoaderImpl(new SlickSoundLoader("assets/sounds/nextwave.wav"))));
			resourceManager.add("SoundSample", new CachedResourceLoader(new ResourceLoaderImpl(new SlickSoundLoader(new ClassPathDataSource("assets/sounds/nextwave.wav")))));
			resourceManager.add("Font", new CachedResourceLoader(new ResourceLoaderImpl(new SlickTrueTypeFontLoader(new ClassPathDataSource("assets/fonts/Mugnuts.ttf"), Font.BOLD, 32))));

			resourceManager.add("BlackLogo", new CachedResourceLoader(new ResourceLoaderImpl<Image>(new SlickImageLoader(new ClassPathDataSource("logo-gemserk-512x116.png")))));
			resourceManager.add("WhiteLogo", new CachedResourceLoader(new ResourceLoaderImpl<Image>(new SlickImageLoader(new ClassPathDataSource("logo-gemserk-512x116-white.png")))));
		}

		taskQueue.add(new SimulateLoadingTimeRunnable(700));
		taskQueue.add(new SimulateLoadingTimeRunnable(300));

		taskQueue.add(new EnterNextStateRunnable(container, this, new TestGameState()));

		addState(new TransitionGameState());
		addState(new LoadingGameState(2, new SlickImageLoader(new ClassPathDataSource("logo-gemserk-512x116-white.png")).load(), taskQueue));
	}

	/**
	 * Only to make the fade in effect when application starts.
	 */
	class TransitionGameState extends BasicGameState {

		@Override
		public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		}

		@Override
		public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		}

		@Override
		public void init(GameContainer container, StateBasedGame game) throws SlickException {
			enterState(2, new EmptyTransition(), new FadeInTransition(Color.black, 500));
		}

		@Override
		public int getID() {
			return 3;
		}
	}

	public class TestGameState extends BasicGameState {

		private Resource<Image> resourceImageA;

		@Override
		public void init(GameContainer container, StateBasedGame game) throws SlickException {
			logger.debug("Init test game state");
			resourceImageA = resourceManager.get("BusinessCard");
		}

		@Override
		public void enter(GameContainer container, StateBasedGame game) throws SlickException {
			logger.debug("Enter test game state");
		}

		@Override
		public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
			Image image = resourceImageA.get();
			g.drawImage(image, 800 / 2 - image.getWidth() / 2, 600 / 2 - image.getHeight() / 2);
		}

		@Override
		public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		}

		@Override
		public int getID() {
			return 1;
		}

	}

}
