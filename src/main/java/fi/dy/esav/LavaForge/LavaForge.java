package fi.dy.esav.LavaForge;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LavaForge extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");

	LavaForgeEntityListener entityListener = new LavaForgeEntityListener();
	
	public void onEnable() {

		log.info(this.getDescription().getName() + " version "
				+ this.getDescription().getVersion() + " started.");

		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(entityListener, this);


	}

	public void onDisable() {

		log.info(this.getDescription().getName() + " version "
				+ this.getDescription().getVersion() + " stopped.");

	}
}
