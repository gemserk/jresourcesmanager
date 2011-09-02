package com.gemserk.resources.tests;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gemserk.resources.Resource;
import com.gemserk.resources.ResourceManager;
import com.gemserk.resources.ResourceManagerImpl;
import com.gemserk.resources.dataloaders.DataLoader;
import com.gemserk.resources.datasources.ClassPathDataSource;
import com.gemserk.resources.datasources.DataSource;

public class ResourcesReloadExample {
	
	static class ImageLoader extends DataLoader<BufferedImage> {
		
		private final DataSource dataSource;

		public ImageLoader(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		@Override
		public BufferedImage load() {
			try {
				BufferedImage image = ImageIO.read(dataSource.getInputStream());
				return image;
			} catch (IOException e) {
				throw new RuntimeException("Failed to read resource for " + dataSource.getResourceName(), e);
			}
		}
		
	}
	
	static class Application {
		
		private ResourceManager<String> resourceManager;


		class MyClass1 {
			
			private final Resource<BufferedImage> imageResource;

			public MyClass1() {
				this.imageResource = resourceManager.get("MyImage");
			}
			
			void update() {
				BufferedImage image = imageResource.get();
				System.out.println("MyClass1: image (" + image.getWidth() + "x" + image.getHeight() + ")");
			}
			
		}
		
		class MyClass2 {
			
			private final Resource<BufferedImage> imageResource;

			public MyClass2() {
				this.imageResource = resourceManager.get("MyImage");
			}
			
			
			void update() {
				BufferedImage image = imageResource.get();
				System.out.println("MyClass2: image (" + image.getWidth() + "x" + image.getHeight() + ")");
			}
			
		}
		

		void run() {
			resourceManager = new ResourceManagerImpl<String>();
			resourceManager.add("MyImage", new ImageLoader(new ClassPathDataSource("logo-gemserk-512x116.png")));
			
			MyClass1 myClass1 = new MyClass1();
			MyClass2 myClass2 = new MyClass2();
			
			myClass1.update();
			myClass2.update();
			
			Resource<BufferedImage> myImage = resourceManager.get("MyImage");
			myImage.setDataLoader(new ImageLoader(new ClassPathDataSource("assets/images/businesscard_front_landscape_ariel_fashionvictim_1.png")));
			
			myClass1.update();
			myClass2.update();
		}
		
	}

	public static void main(String[] args) {
		new Application().run();
	}
}
