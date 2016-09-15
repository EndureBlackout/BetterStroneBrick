package me.endureblackout.bettercrackedstone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BCSMain extends JavaPlugin {
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new BlockListener(this), this);
		getCommand("BSB").setExecutor(new CommandListener(this));
		
		File file = new File(getDataFolder(), "config.yml");
		if(!(file.exists())) {
			try {
				saveConfig();
				setupConfig(getConfig());
				getConfig().options().copyDefaults(true);
				saveConfig();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void setupConfig(FileConfiguration config) throws IOException {
		if (!new File(getDataFolder(), "RESET.FILE").exists()) {
			new File(getDataFolder(), "RESET.FILE").createNewFile();
			ArrayList<String> list = new ArrayList<String>();
			getConfig().set("time to change", 2700);
			list.add("world");
			getConfig().set("worlds", list);
		}
	}
}
