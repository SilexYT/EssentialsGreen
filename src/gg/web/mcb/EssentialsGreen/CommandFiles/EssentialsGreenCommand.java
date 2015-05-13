package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EssentialsGreenCommand implements CommandExecutor {
	main plugin;
	
	public EssentialsGreenCommand(main main){
		main = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(args.length == 0){
			sender.sendMessage(main.prefix + " By Marco606598\n/eg reload");
		}else if(args.length > 0){
			if(args[0].equalsIgnoreCase("reload")){
				if(sender.hasPermission("EssentialsGreen.reload")){
					plugin.reloadConfig();
					sender.sendMessage(main.prefix + "Config RELOADED!");
				}else sender.sendMessage(main.prefix + "You do not have the required permissions");
			}else sender.sendMessage(main.prefix + "\n/eg reload");
		}else sender.sendMessage(main.prefix + "\n/eg reload");
		return true;
	}
}
