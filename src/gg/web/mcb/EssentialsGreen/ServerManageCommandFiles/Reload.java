package gg.web.mcb.EssentialsGreen.ServerManageCommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.ReloadServer")){
			Bukkit.broadcastMessage(EssentialsGreen.prefix + "�2Reload Starting...");
			Bukkit.reload();
			Bukkit.reloadWhitelist();
			Bukkit.broadcastMessage(EssentialsGreen.prefix + "�2Reload Finished!");
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}