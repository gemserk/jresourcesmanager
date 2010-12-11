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
import com.gemserk.resources.monitor.FileMonitorResourceHelperImpl;
import com.gemserk.resources.monitor.FilesMonitor;
import com.gemserk.resources.monitor.FilesMonitorImpl;
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

		filesMonitor = new FilesMonitorImpl();

		FileMonitorResourceHelperImpl fileMonitorResourceHelper = new FileMonitorResourceHelperImpl();
		
		fileMonitorResourceHelper.setFilesMonitor(new FilesMonitorImpl());
		
		new SlickResourcesBuilder(resourceManager, fileMonitorResourceHelper) {
			{
				images("images.properties");
				sounds("sounds.properties");
				truetypefont("MyFont", "assets/fonts/Mugnuts.ttf", java.awt.Font.BOLD, 48);
			}
		};

		companyLogoResource = resourceManager.get("WhiteLogo");
		fileReloadedResource = resourceManager.get("FileReloadedSound");
	}

	private FilesMonitor filesMonitor;

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

	}

	@Override
	public void keyPressed(int key, char c) {

		if (key == Input.KEY_R) {
			// resourcesMonitor.reloadModifiedResources();
			filesMonitor.checkModifiedFiles();
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
