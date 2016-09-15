package me.endureblackout.bettercrackedstone;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
	BCSMain plugin;
	
	public CommandListener(BCSMain instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("BSB") && p.hasPermission("BSB.admin")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("add")) {
						String world = p.getLocation().getWorld().getName();
						
						if(!plugin.getConfig().getStringList("worlds").contains(world)) {
							List<String> list = new ArrayList<String>();
							list.addAll(plugin.getConfig().getStringList("worlds"));
							list.add(world.toLowerCase());
							
							plugin.getConfig().set("worlds", list);
							plugin.saveConfig();
							
							p.sendMessage(ChatColor.GREEN + "World added!");
						}
					} 
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("add")) {
						String world = args[1].toLowerCase();
						
						if(!plugin.getConfig().getStringList("worlds").contains(world)) {
							List<String> list = new ArrayList<String>();
							list.addAll(plugin.getConfig().getStringList("worlds"));
							list.add(world.toLowerCase());
							
							plugin.getConfig().set("worlds", list);
							plugin.saveConfig();
							
							p.sendMessage(ChatColor.GREEN + "World added!");
						}
					}
					if(args[0].equalsIgnoreCase("remove")) {
						if(plugin.getConfig().getStringList("worlds").contains(args[1].toLowerCase())) {
							List<String> list = new ArrayList<String>();
							list.addAll(plugin.getConfig().getStringList("worlds"));
							list.remove(args[1].toLowerCase());
							
							plugin.getConfig().set("worlds", list);
							plugin.saveConfig();
							
							p.sendMessage(ChatColor.GREEN + "World removed!");
						}
					}
				}
			}
		}
		return true;
	}

}
