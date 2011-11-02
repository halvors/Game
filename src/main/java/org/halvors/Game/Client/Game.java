package org.halvors.Game.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.halvors.Game.Client.gui.MainWindow;
import org.halvors.Game.Client.network.NetworkManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	private static Game instance;
	
	private final String name = "Game";
	private final String version = "0.0.2";
	
	private final Logger logger = Logger.getLogger("Game");
	private final KeyManager keyManager;
	private final SoundManager soundManager;
	private final NetworkManager networkManager;
	
	private final List<World> worlds = new ArrayList<World>();
	
	private int width = 800;
	private int height = 600;
	
	public Game() throws LWJGLException {
		Game.instance = this;
		
		this.keyManager = new KeyManager(this);
		this.soundManager = new SoundManager(this);
		this.networkManager = new NetworkManager(this);
	}
	
	public void main(String[] args) throws LWJGLException {
		MainWindow mainWindow = new MainWindow(this);
		
//		initialize();
	}
	
	private void initialize() throws LWJGLException {
		Display.setTitle(name + " " + version);
		
		try {
			// Initialize Display.
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			
			// Initialize Keyboard and Mouse.
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		// Initialize OpenGL.
		GL11.glMatrixMode(GL11.GL_4D_COLOR_TEXTURE);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		while (!Display.isCloseRequested()) {
			// TODO: Render OpenGL here
			loadScreen();
			
			Display.update();
			
			// FPS limit to 60.
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	private void loadScreen() throws LWJGLException {
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5F, 0.5F, 1.0F);
	}

	public static Game getInstance() {
		return instance;
	}
	
	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public void log(Level level, String message) {
		logger.log(level, message);
	}
	
	public List<World> getWorlds() {
		return worlds;
	}
	
	public World getWorld(UUID id) {
		for (World world : worlds) {
			if (id.equals(world.getId())) {
				return world;
			}
		}
		
		return null;
	}
	
	public World getWorld(String name) {
		for (World world : worlds) {
			if (name.equals(world.getName())) {
				return world;
			}
		}
		
		return null;
	}
	
	public boolean hasWorld(World world) {
		return worlds.contains(world);
	}
	
	public World addWorld(World world) {
		if (world != null && !worlds.contains(world)) {
			worlds.add(world);
			
			return world;
		}
		
		return null;
	}
	
	public World addWorld(String name) {
		return addWorld(new World(name));
	}
	
	public void removeWorld(UUID id) {
		World world = getWorld(id);
		
		if (world != null) {
			worlds.remove(world);
		}
	}
	
	public void removeWorld(String name) {
		World world = getWorld(name);
		
		if (world != null) {
			worlds.remove(world);
		}
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}