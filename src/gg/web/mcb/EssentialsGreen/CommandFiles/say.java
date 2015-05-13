package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
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
				String Text = "";
				for(int i = 0; args.length > i; i++){
					Text = Text + " " + args[i];
				}
				Bukkit.broadcastMessage("§4[SAY] §e" + Text.replace('&', '§'));
				sender.sendMessage(EssentialsGreen.prefix + "Message sent!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}