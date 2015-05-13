package gg.web.mcb.EssentialsGreen.ListenerFiles;

import java.util.ArrayList;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

@SuppressWarnings("deprecation")
public class ExplosionListener implements Listener{
	
	EssentialsGreen plugin;
	ArrayList<Material> db = new ArrayList<Material>();
	
	public ExplosionListener(EssentialsGreen main) {
		plugin = main;
		db.add(Material.BEDROCK);
		db.add(Material.AIR);
		db.add(Material.OBSIDIAN);
	}
	
	@EventHandler
	public void ExplosionEvent(EntityExplodeEvent e){
		if(e.getEntityType().equals(EntityType.CREEPER)){
			if(!plugin.getConfig().getString("CreeperBlockDamage").equalsIgnoreCase("true")){
				e.blockList().clear();
			}
		}else if(e.getEntityType().equals(EntityType.PRIMED_TNT)){
			if(!plugin.getConfig().getString("TnTBlockDamage").equalsIgnoreCase("true")){
				e.blockList().clear();
			}
		}else if(e.getEntityType().equals(EntityType.WITHER_SKULL)){
		if(!plugin.getConfig().getString("WhitherSkullBlockDamage").equalsIgnoreCase("true")){
			e.blockList().clear();
		}
	}
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
						if(!db.contains(b.getType())){
							FallingBlock fb = World.spawnFallingBlock(b.getLocation(), b.getType(), (byte)b.getData());
							fb.setDropItem(false);
							fb.setVelocity(new Vector(x,y,z));
						}
					}
				}
			}
		}
	}
}
