package gg.web.mcb.EssentialsGreen.CommandFiles;

import java.io.File;

import gg.web.mcb.EssentialsGreen.MainPackage.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class banlist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.Banlist")){
			File F = new File("plugins/EssentialsGreen/UserData");
			File[] list = F.listFiles();
			int Banned = 0;
			sender.sendMessage(main.prefix + "[Banlist]");
			for(int i = 0; i < list.length; i++){
				YamlConfiguration Player = YamlConfiguration.loadConfiguration(list[i]);
				if(Player.getString("Ban.Enable").equalsIgnoreCase("true")){
					String Name = list[i].getName();
					sender.sendMessage("§e" + Name + " | Reason : " + Player.getString("Ban.Reason"));
					Banned++;
				}
			}
			sender.sendMessage("§3There are " + Banned + " Players banned");
		}else sender.sendMessage(main.prefix + "You do not have the required permissions");
		return true;
	}
}