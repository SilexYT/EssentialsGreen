package me.noip.ccbluex.EssentialsGreen.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.APIs.StringAPI;

public class broadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.broadcast")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/broadcast <Message...>");
			}else if(args.length > 0){
				String Text = StringAPI.toCompleteString(args, 0).replace('&', '§');
				Bukkit.broadcastMessage("§3[§lBrodcast§r§3] §e--->§f " + Text);
				sender.sendMessage(EssentialsGreen.prefix + "Message sent!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}