package me.noip.ccbluex.EssentialsGreen.Listeners;

import org.bukkit.EntityEffect;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public class PhysicExplosionListener implements Listener {

	EssentialsGreen plugin;
	
	public PhysicExplosionListener(EssentialsGreen p) {
		plugin = p;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ExplosionEvent(EntityExplodeEvent e){
		//Physic Explosion
		if(!e.isCancelled()){
			if(!e.blockList().isEmpty()){
				String pea = plugin.getConfig().getString("PhysicExplosion");
				if(pea.equalsIgnoreCase("true")){
					e.setYield(0F);
					double x = (double)(Math.random() * 0.2D);
					double y = 0.5D;
					double z = (double)(Math.random() * 0.1D);
					World World = e.getLocation().getWorld();
					for(int i = 0; i < e.blockList().size(); i++){
						Block b = e.blockList().get(i);
						FallingBlock fb = World.spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
						fb.setDropItem(true);
						fb.setVelocity(new Vector(x,y,z));
						fb.playEffect(EntityEffect.DEATH);
					}
				}
			}
		}
	}
}