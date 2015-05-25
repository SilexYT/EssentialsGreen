package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class list implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.list")){
			int MP = Bukkit.getMaxPlayers();
			sender.sendMessage(EssentialsGreen.prefix + " [List]\nThere are " + Bukkit.getOnlinePlayers().size() + "/" + MP);
			for(Player p : Bukkit.getOnlinePlayers()){
				sender.sendMessage("§e" + p.getName());
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}