package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class seed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.seed")){
				if(args.length > 0){
					World W = Bukkit.getWorld(args[0]);
					if(!(W == null)){
						p.sendMessage(EssentialsGreen.prefix + "Seed: " + W.getSeed());
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World exist not!");
				}else{
					World W = p.getWorld();
					p.sendMessage(EssentialsGreen.prefix + "Seed: " + W.getSeed());
				}
			}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		}else{
			if(args.length > 0){
				World W = Bukkit.getWorld(args[0]);
				if(!(W == null)){
					sender.sendMessage(EssentialsGreen.prefix + "Seed: " + W.getSeed());
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World exist not!");
			}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] provide a World");
		}
		return true;
	}
}