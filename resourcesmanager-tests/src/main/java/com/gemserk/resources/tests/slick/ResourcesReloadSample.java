package com.gemserk.resources.tests.slick;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.resourceloaders.ResourceLoaderImpl;
import com.gemserk.resources.slick.dataloaders.SlickImageLoader;

public class ResourcesReloadSample extends BasicGame {

	ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

	Resource<Image> companyLogoResource;

	public ResourcesReloadSample(String title) {
		super(title);
	}

	public static void main(String[] arguments) throws SlickException {

		AppGameContainer app = new AppGameContainer(new ResourcesReloadSample(ResourcesReloadSample.class.getName()));

		app.setDisplayMode(640, 480, false);
		app.setAlwaysRender(true);

		app.setVSync(true);

		app.setShowFPS(true);
		app.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {

		resourceManager.add("CompanyLogo", new ResourceLoaderImpl<Image>(new SlickImageLoader("logo-gemserk-512x116-white.png"), true));

		companyLogoResource = resourceManager.get("CompanyLogo");

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

	}

	@Override
	public void keyPressed(int key, char c) {

		if (key == Input.KEY_R ) {
			companyLogoResource.reload();
		}
		
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {

	}

	@Override
	public void mouseWheelMoved(int change) {

	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {

		Image image = companyLogoResource.get();
		g.drawImage(image, 320 - image.getWidth() / 2, 240 - image.getHeight() / 2);

	}

}
