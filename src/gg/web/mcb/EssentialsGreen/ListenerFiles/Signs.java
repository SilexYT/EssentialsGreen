package gg.web.mcb.EssentialsGreen.ListenerFiles;

import gg.web.mcb.EssentialsGreen.ApiFiles.ItemManager;
import gg.web.mcb.EssentialsGreen.ApiFiles.NumberManager;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Signs implements Listener {
	
	@EventHandler
	public void PlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getClickedBlock().getState() instanceof Sign){
				Sign s = (Sign)e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase("§1[Free]")){
					if(!s.getLine(1).equalsIgnoreCase("§4Error Item")){
						ItemStack Item = new ItemStack(ItemManager.getMaterialByID(new Integer(s.getLine(1))));						p.getInventory().addItem(Item);
						p.updateInventory();
					}
				}else if(s.getLine(0).equalsIgnoreCase("§1[Command]")){
					p.performCommand(s.getLine(1));
				}
			}
		}
	}
	
	@EventHandler
	public void SignChangeEventClass(SignChangeEvent e){
		e.setLine(0, e.getLine(0).replace('&', '§'));
		e.setLine(1, e.getLine(1).replace('&', '§'));
		e.setLine(2, e.getLine(2).replace('&', '§'));
		e.setLine(3, e.getLine(3).replace('&', '§'));
		if(e.getLine(0).equalsIgnoreCase("[Free]")){
			e.setLine(0, "§1[Free]");
			if(e.getPlayer().hasPermission("EssentialsGreen.create.FreeSign")){
				if(!NumberManager.IsStringint(e.getLine(1))){
					e.setLine(1, "§4Error Item");
				}
			}
		}else if(e.getLine(0).equalsIgnoreCase("[Command]")){
			if(e.getPlayer().hasPermission("EssentialsGreen.create.CommandSign")){
				e.setLine(0, "§1[Command]");
			}
		}
	}
}
