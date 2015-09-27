package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reload implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.ReloadServer")){
			Bukkit.broadcastMessage(EssentialsGreen.prefix + "§2Reload Starting By " + sender.getName() + "...");
			Bukkit.reload();
			Bukkit.reloadWhitelist();
			Bukkit.broadcastMessage(EssentialsGreen.prefix + "§2Reload Finished!");
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}