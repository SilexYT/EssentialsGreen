package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class defaultgamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.defaultgamemode")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/defaultgamemode <0|1|2|3>");
			}else{
				if(args[0].equalsIgnoreCase("0") | args[0].equalsIgnoreCase("survival")){
					Bukkit.setDefaultGameMode(GameMode.SURVIVAL);
					sender.sendMessage(EssentialsGreen.prefix + "The Default Gamemode is now Survival");
				}else if(args[0].equalsIgnoreCase("1") | args[0].equalsIgnoreCase("creative")){
					Bukkit.setDefaultGameMode(GameMode.CREATIVE);
					sender.sendMessage(EssentialsGreen.prefix + "The Default Gamemode is now Creative");
				}else if(args[0].equalsIgnoreCase("2") | args[0].equalsIgnoreCase("adventure")){
					Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
					sender.sendMessage(EssentialsGreen.prefix + "The Default Gamemode is now Adventure");
				}else if(args[0].equalsIgnoreCase("3") | args[0].equalsIgnoreCase("spectator")){
					Bukkit.setDefaultGameMode(GameMode.SPECTATOR);
					sender.sendMessage(EssentialsGreen.prefix + "The Default Gamemode is now Spectator");
				}else sender.sendMessage(EssentialsGreen.prefix + "/defaultgamemode <0|1|2|3>");
			}
		}
		return true;
	}
}