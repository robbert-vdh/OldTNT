package me.coolblinger.oldtnt;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OldTNT extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	OldTNTBlockListener blockListener = new OldTNTBlockListener(this);
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Event.Priority.Normal, this);
		log.info(pdFile.getName() + " version " + pdFile.getVersion() + " loaded!");
	}
}
