package main.java.org.halvors.Game.Client;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.org.halvors.Game.Client.gui.Menu;

public class Client {
	private static Client instance;
	
	private final String name = "Game";
	private final String version = "0.0.1";
	
	private final Logger logger = Logger.getLogger("Game");
	private final NetworkManager networkManager = new NetworkManager();
	
	public Client() {
		Client.instance = this;	
		
		// Create the menu.
		Menu menu = new Menu();
	}
	
	public void main(String[] args) {
		
	}
	
	public static Client getInstance() {
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
}
