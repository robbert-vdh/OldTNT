package me.coolblinger.oldtnt;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public class OldTNT extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	OldTNTBlockListener blockListener = new OldTNTBlockListener(this);
	OldTNTConfig config = new OldTNTConfig(this);
	List<String> ignoredWorldsList = config.getIgnoredWorlds();
	
	@Override
	public void onDisable() {

	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Event.Priority.Normal, this);
		log.info(pdFile.getName() + " version " + pdFile.getVersion() + " loaded!");
	}
}
