package me.noip.ccbluex.EssentialsGreen.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.APIs.StringAPI;

public class asConsole implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.asConsole")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/asConsole <Command...>");
			}else{
				String command = StringAPI.toCompleteString(args, 0);
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
				sender.sendMessage(EssentialsGreen.prefix + "The Command is send!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}