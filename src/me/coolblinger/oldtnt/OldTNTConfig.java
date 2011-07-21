package me.coolblinger.oldtnt;

import org.bukkit.util.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"ALL"})
public class OldTNTConfig {
	public OldTNT plugin;
	File configFile = new File("plugins" + File.separator + "OldTNT" + File.separator + "config.yml");

	public OldTNTConfig(OldTNT instance) {
    	plugin = instance;
		initConfig();
    }

	public Configuration config() {
		try {
			Configuration config = new Configuration(configFile);
			config.load();
			return config;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getIgnoredWorlds() {
		Configuration config = config();
		List<String> ignoredWorldsList = (List)config.getProperty("worlds.ignore");
		if (ignoredWorldsList != null) {
			return ignoredWorldsList;
		} else {
			return null;
		}
	}

	public void initConfig() {
		configFile.getParentFile().mkdir();
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Configuration config = config();
		if (!config.getKeys().contains("worlds.ignore"))
		{
			List<String> dummyList = new ArrayList<String>();
			dummyList.add("ignoredworld");
			dummyList.add("WaffleWorld");

			config.setProperty("worlds.ignore", dummyList);
			config.save();
		}
	}
}
