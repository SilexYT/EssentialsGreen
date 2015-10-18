package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.APIs.StringAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.tp")){
			if(args.length == 1){
				if(sender instanceof Player){
					Player p = (Player)sender;
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null){
						p.teleport(target);
						p.sendMessage(EssentialsGreen.prefix + "Teleport...");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online");
				}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youmustplayer"));
			}else if(args.length == 2){
				Player target = Bukkit.getPlayer(args[0]);
				Player dp = Bukkit.getPlayer(args[1]);
				if(target != null){
					if(dp != null){
						target.teleport(dp);
						target.sendMessage(EssentialsGreen.prefix + "Teleport...");
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This destination player is not online");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The target player is not online");
			}else if(args.length == 4){
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(StringAPI.isInteger(args[0])){
						if(StringAPI.isInteger(args[1])){
							if(StringAPI.isInteger(args[2])){
								World w = Bukkit.getWorld(args[3]);
								if(w != null){
									Double x = new Double(args[0]);
									Double y = new Double(args[1]);
									Double z = new Double(args[2]);
									Location loc = new Location(w, x, y, z);
									p.teleport(loc);
								}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World '" + args[3] + "' exist not!");
							}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] '" + args[2] + "' is not a number!");
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] '" + args[1] + "' is not a number!");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] '" + args[0] + "' is not a number!");
				}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youmustplayer"));
			}else if(args.length > 4){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(StringAPI.isInteger(args[1])){
						if(StringAPI.isInteger(args[2])){
							if(StringAPI.isInteger(args[3])){
								World w = Bukkit.getWorld(args[4]);
								if(w != null){
									Double x = new Double(args[1]);
									Double y = new Double(args[2]);
									Double z = new Double(args[3]);
									Location loc = new Location(w, x, y, z);
									target.teleport(loc);
								}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World '" + args[4] + "' exist not!");
							}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] '" + args[2] + "' is not a number!");
						}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] '" + args[1] + "' is not a number!");
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] '" + args[0] + "' is not a number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The target player is not online");
			}else 				sender.sendMessage(EssentialsGreen.prefix + "/tp [target player] <destination player> or /tp [target player] <x> <y> <z> <World>");
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}