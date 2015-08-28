package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpall implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.tp")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player p = (Player)sender;
					for(Player all : Bukkit.getOnlinePlayers()){
						all.teleport(p);
						all.sendMessage(EssentialsGreen.prefix + "Teleport...");
					}
					sender.sendMessage(EssentialsGreen.prefix + "All players to " + p.getName() + " teleported!");
				}else sender.sendMessage(EssentialsGreen.prefix + "/tpall <target>");
			}else if(args.length > 0){
				Player t = Bukkit.getPlayer(args[0]);
				if(t != null){
					for(Player all : Bukkit.getOnlinePlayers()){
						all.teleport(t);
						all.sendMessage(EssentialsGreen.prefix + "Teleport...");
					}
					sender.sendMessage(EssentialsGreen.prefix + "All players to " + t.getName() + " teleported!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}