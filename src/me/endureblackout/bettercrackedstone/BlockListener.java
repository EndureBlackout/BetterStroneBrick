package me.endureblackout.bettercrackedstone;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockListener implements Listener {
	BCSMain plugin;
	
	public BlockListener(BCSMain instance) {
		this.plugin = instance;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockDestroy(BlockBreakEvent e) {
		Player p = e.getPlayer();
		final Block b = e.getBlock();
		World world = p.getLocation().getWorld();
		
		for(String y : plugin.getConfig().getStringList("worlds")) {
			if(p.getGameMode() == GameMode.SURVIVAL) {
				if(world.getName().equalsIgnoreCase(y)) {
					if(b.getType() == Material.SMOOTH_BRICK && b.getData() == (byte)0) {
						b.setData((byte)2);
						e.setCancelled(true);
						
						plugin.getServer().getScheduler().scheduleAsyncDelayedTask(this.plugin, new BukkitRunnable() {
							public void run() {
								b.setData((byte)1);
							}
						}, plugin.getConfig().getInt("time to change") * 20);
					} else if(b.getType() == Material.SMOOTH_BRICK && b.getData() == (byte)2) {
						e.setCancelled(false);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		World world = p.getLocation().getWorld();
		
		for(String y : plugin.getConfig().getStringList("worlds")) {
			if(p.getGameMode() == GameMode.SURVIVAL) {
				if(world.getName().equalsIgnoreCase(y)) {
					if(e.getClickedBlock().getType() == Material.SMOOTH_BRICK) {
						final Block b = e.getClickedBlock();
						
						if(b.getData() == (byte)1) {
							if(p.getItemInHand().getType() == Material.SHEARS) {
								b.setData((byte)2);
								
								p.getInventory().addItem(new ItemStack(Material.VINE));
								
								plugin.getServer().getScheduler().scheduleAsyncDelayedTask(this.plugin, new BukkitRunnable() {
									public void run() {
										b.setData((byte)1);
									}
								}, plugin.getConfig().getInt("time to change") * 20);
							}
						}
					}
				}
			}
		}
	}
}
