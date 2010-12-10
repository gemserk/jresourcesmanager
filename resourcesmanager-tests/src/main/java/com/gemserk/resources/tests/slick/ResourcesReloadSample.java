package com.gemserk.resources.tests.slick;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.monitor.ResourcesMonitor;
import com.gemserk.resources.monitor.ResourcesMonitorImpl;
import com.gemserk.resources.slick.SlickResourcesBuilder;

public class ResourcesReloadSample extends BasicGame {

	ResourceManager<String> resourceManager = new ResourceManagerImpl<String>();

	Resource<Image> companyLogoResource;

	private Resource<Sound> fileReloadedResource;

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

		resourcesMonitor = new ResourcesMonitorImpl();

		new SlickResourcesBuilder(resourceManager, resourcesMonitor) {
			{
				// image("CompanyLogo", "logo-gemserk-512x116-white.png");
				// sound("FileReloadedSound", "assets/sounds/nextwave.wav");
				truetypefont("MyFont", "assets/fonts/Mugnuts.ttf", java.awt.Font.BOLD, 48);

				images("images.properties");
				sounds("sounds.properties");
			}
		};

		companyLogoResource = resourceManager.get("WhiteLogo");
		fileReloadedResource = resourceManager.get("FileReloadedSound");
	}

	private ResourcesMonitor resourcesMonitor;

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

	}

	@Override
	public void keyPressed(int key, char c) {

		if (key == Input.KEY_R) {
			resourcesMonitor.reloadModifiedResources();
			fileReloadedResource.get().play();
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

		Resource<Font> fontResource = resourceManager.get("MyFont");
		g.setColor(Color.white);
		g.setFont(fontResource.get());
		g.drawString("Hello world", 320 - image.getWidth() / 2, 100);
	}

}
