package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class nick implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.nick")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/nick <Name|off>");
				}else if(args.length > 0){
					if(args[0].equalsIgnoreCase("off")){
						String name = p.getName();
						p.setCustomName(name);
						p.setDisplayName(name);
						p.setPlayerListName(name);
						p.sendMessage(EssentialsGreen.prefix + "Your name is now again " + name);
					}else{
						p.setCustomName(args[0]);
						p.setDisplayName(args[0]);
						p.setPlayerListName(args[0]);
						p.sendMessage(EssentialsGreen.prefix + "Your name is now " + args[0]);
					}
				}
			}else sender.sendMessage(EssentialsGreen.prefix + "You must be a Player");
		}
		return true;
	}
}