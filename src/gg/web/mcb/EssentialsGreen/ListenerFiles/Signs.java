package gg.web.mcb.EssentialsGreen.ListenerFiles;

import gg.web.mcb.EssentialsGreen.API.ItemManager;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Signs implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void PlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getClickedBlock().getState() instanceof Sign){
				Sign s = (Sign)e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase("§1[Free]")){
					if(!s.getLine(1).equalsIgnoreCase("§4Error Item")){
						String[] LineSplit = s.getLine(1).split(":");
						ItemStack Item = new ItemStack(new Integer(LineSplit[0]), 1, (short) 0, new Byte(LineSplit[1]));
						p.getInventory().addItem(Item);
						p.updateInventory();
					}
				}else if(s.getLine(0).equalsIgnoreCase("§1[Command]")){
					p.performCommand(s.getLine(1));
				}else if(s.getLine(0).equalsIgnoreCase("§1[Warp]")){
					if(!s.getLine(1).equalsIgnoreCase("<Warp>")){
						p.performCommand("warp " + s.getLine(1));
					}
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
		String Line1 = e.getLine(1);
		if(e.getLine(0).equalsIgnoreCase("[Free]")){
			if(e.getPlayer().hasPermission("EssentialsGreen.create.FreeSign")){
				if(Line1 != null){
					e.setLine(0, "§1[Free]");
					if(Line1.split(":").length != 0){
						String[] LineSplit = Line1.split(":");
						if(new Integer(LineSplit[0]) != null & new Integer(LineSplit[1]) != null){
							int ID = new Integer(LineSplit[0]);
							if(!ItemManager.CheckID(ID)){
								e.setLine(1, "§4Error Item");
							}
						}else e.setLine(1, "§4Error Item");
					}else e.setLine(1, "<ID:SubID>");
				}else e.setLine(1, "<ID:SubID>");
			}
		}else if(e.getLine(0).equalsIgnoreCase("[Command]")){
			if(e.getPlayer().hasPermission("EssentialsGreen.create.CommandSign")){
				e.setLine(0, "§1[Command]");
			}
		}else if(e.getLine(0).equalsIgnoreCase("[Warp]")){
			if(e.getPlayer().hasPermission("EssentialsGreen.create.WarpSign")){
				e.setLine(0, "§1[Warp]");
				if(e.getLine(1) == null){
					e.setLine(1, "<Warp>");
				}
			}
		}
	}
}