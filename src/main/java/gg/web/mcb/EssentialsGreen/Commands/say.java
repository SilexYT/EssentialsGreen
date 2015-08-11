package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import gg.web.mcb.EssentialsGreen.util.StringAPI;

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
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}