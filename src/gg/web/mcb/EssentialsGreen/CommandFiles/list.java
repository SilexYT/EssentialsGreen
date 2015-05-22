package gg.web.mcb.EssentialsGreen.CommandFiles;

import java.util.ArrayList;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class list implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.list")){
			ArrayList<String> P = EssentialsGreen.OnlinePlayers;
			int MP = Bukkit.getMaxPlayers();
			sender.sendMessage(EssentialsGreen.prefix + " [List]\nThere are " + P.size() + "/" + MP);
			for(int i = 0; i < P.size(); i++){
				sender.sendMessage("§e" + P.get(i));
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}