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
			EntityTNTPrimed tnt = new EntityTNTPrimed(cWorld.getHandle(), block.getX(), block.getY(), block.getZ());
			tnt.setPositionRotation(block.getLocation().getBlockX() + 0.5, block.getLocation().getBlockY(), block.getLocation().getBlockZ() + 0.5, 0, 0);
			cWorld.getHandle().addEntity(tnt);
			if (tnt.fuseTicks > 0) // I added this check because Eclipse would otherwise give warnings about the fact that primedTNT is never used.
			{
				block.setType(Material.AIR);
			}
		}
	}
}
