package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class tree implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.tree")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/tree <Oak,Spruce,Birch,Jungle,Acacia,Dark_Oak>");
			}
		}
		return true;
	}
}