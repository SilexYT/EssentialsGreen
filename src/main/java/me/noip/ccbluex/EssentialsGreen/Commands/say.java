package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.APIs.StringAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class say implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.say")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/say <Message>");
			}else if(args.length > 0){
				String Text = StringAPI.toCompleteString(args, 0).replace('&', '§');
				Bukkit.broadcastMessage("§3[§lSay§r§3] §e--->§f " + Text);
				sender.sendMessage(EssentialsGreen.prefix + "Message sent!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}