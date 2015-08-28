package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class list implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.list")){
			sender.sendMessage(EssentialsGreen.prefix + "[List]\nThere are " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			for(Player p : Bukkit.getOnlinePlayers()){
				sender.sendMessage("§e" + p.getName());
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}