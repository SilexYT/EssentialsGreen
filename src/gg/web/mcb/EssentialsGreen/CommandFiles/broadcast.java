package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class broadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.broadcast")){
			if(args.length == 0){
				sender.sendMessage(main.prefix + "/broadcast <Message>");
			}else if(args.length > 0){
				String Text = "";
				for(int i = 0; args.length > i; i++){
					Text = Text + " " + args[i];
				}
				Bukkit.broadcastMessage("§4[Brodcast] §e" + Text.replace('&', '§'));
				sender.sendMessage(main.prefix + "Message sent!");
			}
		}else sender.sendMessage(main.prefix + "You do not have the required permissions");
		return true;
	}

}
