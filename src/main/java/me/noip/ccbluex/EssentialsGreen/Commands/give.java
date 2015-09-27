package me.noip.ccbluex.EssentialsGreen.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.util.ItemManagerAPI;

public class give implements CommandExecutor {

	@SuppressWarnings({"deprecation" })
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args){
		if(p.hasPermission("EssentialsGreen.give")){
			if(args.length == 0){
				p.sendMessage(EssentialsGreen.prefix + "/give <Player> <Materiel|ID> [Ammount] [SubID]");
			}else if(args.length > 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(!(target == null)){
					if(Integer.getInteger(args[1]) != null){
						if(ItemManagerAPI.CheckID(Integer.getInteger(args[1])) == true){
							if(args.length == 2){
								Material Materiel = ItemManagerAPI.getMaterialByID(new Integer(args[1]));
								target.getInventory().addItem(new ItemStack(Materiel, 1));
								p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : 1");
							}else if(args.length == 3){
								Material Materiel = ItemManagerAPI.getMaterialByID(new Integer(args[1]));
								target.getInventory().addItem(new ItemStack(Materiel, new Integer(args[2])));
								p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : " + args[2]);
							}else{
								Material Materiel = ItemManagerAPI.getMaterialByID(new Integer(args[1]));
								target.getInventory().addItem(new ItemStack(Materiel, new Integer(args[2]), (short)0, new Byte(args[3])));
								p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : " + args[2]);
							}
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] ID not Found");
					}else if(ItemManagerAPI.CheckMateriel(args[1])){
						if(args.length == 2){
							Material Materiel = Material.matchMaterial(args[1]);
							target.getInventory().addItem(new ItemStack(Materiel, 1));
							p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : 1");
						}else if(args.length == 3){
							Material Materiel = Material.matchMaterial(args[1]);
							target.getInventory().addItem(new ItemStack(Materiel, new Integer(args[2])));
							p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : " + args[2]);
						}else{
							Material Materiel = Material.matchMaterial(args[1]);
							target.getInventory().addItem(new ItemStack(Materiel, new Integer(args[2]), (short)0, new Byte(args[3])));
							p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : " + args[2]);
						}
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Materiel not Found");
				}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The target player is not online");
			}
		}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}