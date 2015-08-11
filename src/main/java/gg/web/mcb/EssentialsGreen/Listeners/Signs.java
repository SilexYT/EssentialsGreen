package gg.web.mcb.EssentialsGreen.Listeners;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import gg.web.mcb.EssentialsGreen.util.ItemManagerAPI;
import gg.web.mcb.EssentialsGreen.util.StringAPI;

import org.bukkit.Bukkit;
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
	public void onPlayerInteract(PlayerInteractEvent e){
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
					p.performCommand(s.getLine(1).replace("@p", p.getName()));
				}else if(s.getLine(0).equalsIgnoreCase("§1[CCommand]")){
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.getLine(1).replace("@p", p.getName()));
				}else if(s.getLine(0).equalsIgnoreCase("§1[Warp]")){
					if(!s.getLine(1).equalsIgnoreCase("<Warp>")){
						p.performCommand("warp " + s.getLine(1));
					}
				}
			}
		}
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e){
		Player p = e.getPlayer();
		String[] Lines = e.getLines();
		e.setLine(0, Lines[0].replace('&', '§'));
		e.setLine(1, Lines[1].replace('&', '§'));
		e.setLine(2, Lines[2].replace('&', '§'));
		e.setLine(3, Lines[3].replace('&', '§'));
		String Line1 = e.getLine(1);
		if(e.getLine(0).equalsIgnoreCase("[Free]") | e.getLine(0).equalsIgnoreCase("§1[Free]")){
			if(p.hasPermission("EssentialsGreen.create.FreeSign")){
				if(Line1 != null){
					e.setLine(0, "§1[Free]");
					if(Line1.split(":").length == 2){
						String[] LineSplit = Line1.split(":");
						if(StringAPI.getCount(Line1, ':') == 1){
							int ID = Integer.getInteger(LineSplit[0]);
							if(!ItemManagerAPI.CheckID(ID)){
								e.setLine(1, "§4Error Item");
							}
						}else e.setLine(1, "§4Error Item");
					}else e.setLine(1, "<ID:SubID>");
				}else e.setLine(1, "<ID:SubID>");
			}
		}else if(e.getLine(0).equalsIgnoreCase("[Command]") | e.getLine(0).equalsIgnoreCase("§1[Command]")){
			if(p.hasPermission("EssentialsGreen.create.CommandSign")){
				String[] command = e.getLine(1).split(" ");
				if(!command[0].equalsIgnoreCase("op")){
					e.setLine(0, "§1[Command]");
				}else{
					e.getBlock().breakNaturally();
					p.sendMessage(EssentialsGreen.prefix + "This command must not be set command sign!");
				}
			}
		}else if(e.getLine(0).equalsIgnoreCase("[CCommand]") | e.getLine(0).equalsIgnoreCase("§1[CCommand]")){
			if(p.hasPermission("EssentialsGreen.create.ConsoleCommandSign")){
				String[] command = e.getLine(1).split(" ");
				if(!command[0].equalsIgnoreCase("op")){
					e.setLine(0, "§1[CCommand]");
				}else{
					e.getBlock().breakNaturally();
					p.sendMessage(EssentialsGreen.prefix + "This command must not be set command sign!");
				}
			}
		}else if(e.getLine(0).equalsIgnoreCase("[Warp]") | e.getLine(0).equalsIgnoreCase("§1[Warp]")){
			if(p.hasPermission("EssentialsGreen.create.WarpSign")){
				e.setLine(0, "§1[Warp]");
				if(e.getLine(1) == null){
					e.setLine(1, "<Warp>");
				}
			}
		}
	}
}