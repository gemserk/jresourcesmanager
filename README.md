This project provides a way to manage your application resources in a common way.

Features
------------

* User defined resource types (a Font, a String, an Image)
* User defined data loaders, a way to use the user defined resource types.
* Dynamic resource reloading in a transparent way
* Resource cache
* Simple and easy to use.

How to use it
------------

There are three classes to know, DataLoader<T>, Resource<T> and ResourceManager.

### DataLoader 

This class provides the logic of loading and unloading a resource, it is used by the ResourceManager when building a new Resource. This class should be implemented to define the load and unload custom logic, for example, if you want to load a Slick2D Sound, you could implement something like this:

	public class SlickSoundLoader extends DataLoader<Sound> {

		private final String file;

		public SlickSoundLoader(String file) {
			this.file = file;
		}

		@Override
		public Sound load() {
			try {
				return new Sound(file);
			} catch (SlickException e) {
				throw new RuntimeException("failed to load slick sound from file " + file, e);
			}
		}

		public void unload(Sound s) {
			// release the data in some way, most of the time just this method is not implemented.
		}
	
	}

The module named resourcesmanager-slick provides DataLoader implementations for some of the Slick assets like Sound, Image and Fonts, the module named resourcesmanager-java2d provides DataLoader implementations to load java2d Images and others.

### Resource

This class provides a level of abstraction to the real resource data, and provides methods for getting the data, loading it and unloading it. Here is the API of the class:

	Resource<T> {

		// it will return the real data, if not loaded it will try to load it before.
		T get();

		// shortcut to call unload() and load()
		void reload();

		// loads the data if not loaded already
		void load();

		// unloads the data if it was loaded
		void unload();

		// returns true if data is loaded, false otherwise.
		boolean isLoaded();

	}

### ResourceManager

This class provides a way to declare resources by declaring an id and how the Resource is loaded, for example:

	ResourceManager resourceManager = new ResourceManagerImpl();
	resourceManager.add("MyResourceID", new SlickSoundLoader("data/sounds/mysound.ogg"));

By declaring a resource you are not loading it yet, it only defines how should be loaded when you want to get the data:

	Resource<Sound> mySound = resourceManager.get("MyResourceID");
	// here is where the data is loaded for the first time.
	Sound sound = mySound.get();
	// calling get() a second time will return the same already loaded data.
	Sound sound = mySound.get();

### Dynamic resource reloading

An interesting point is Resources are cached in ResourceManager, so if you ask for the Resource identified by mySound in two different parts of the code, both will be accessing the same Resource. That means if you reload it for some reason, the data will be updated in the Resource and you will be implicitly aware of the change in your code without having to do anything extra.

The following video named [Dynamic Resources Reloading](http://www.youtube.com/watch?v=cik0SBRPpiA) shows a resource being modified by an external application and being reloaded.

Also there is an example resourcemanager-tests/src/main/java/com/gemserk/resources/tests/ResourcesReloadExample.java which shows how to change a Resource data on the fly.

Contributing
------------

Feel free to add issues, make forks or contact us directly if you have suggestions, bug reports or enhancements.

