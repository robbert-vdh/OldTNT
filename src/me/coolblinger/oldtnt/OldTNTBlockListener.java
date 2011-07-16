package me.coolblinger.oldtnt;

import net.minecraft.server.EntityTNTPrimed;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

public class OldTNTBlockListener extends BlockListener {
	public OldTNT plugin;
	
	public OldTNTBlockListener(OldTNT instance) {
		plugin = instance;
	}
	
	public void onBlockDamage(BlockDamageEvent event) {
		Block block = event.getBlock();
		if (block.getType() == Material.TNT && !event.isCancelled()) {
			CraftWorld cWorld = (CraftWorld) block.getWorld();
			EntityTNTPrimed tnt = new EntityTNTPrimed(cWorld.getHandle(), block.getX() + 0.5D, block.getY() + 0.5D, block.getZ() + 0.5D);
			cWorld.getHandle().addEntity(tnt);
			// The below code's purpose is to make the transition from a TNT block to primed TNT look a little bit better.
			final Block block2 = block;
			block.setType(Material.AIR);
			block2.getLocation().getBlock().setType(Material.TNT);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			    public void run() {
					block2.setType(Material.AIR);
			    }
			}, 1L);
			event.setCancelled(true);
		}
	}
}
