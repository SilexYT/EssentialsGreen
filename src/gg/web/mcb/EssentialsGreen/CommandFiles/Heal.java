package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.Heal")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player p = (Player)sender;
					p.setHealth(20.0);
					p.sendMessage(EssentialsGreen.prefix + "You'd healed");
				}else sender.sendMessage(EssentialsGreen.prefix + "You must be a Player!");
			}else{
				Player p = Bukkit.getPlayer(args[0]);
				if(!(p == null)){
					p.setHealth(20.0);
					p.sendMessage(EssentialsGreen.prefix + "You've healed " + args[0]);
					sender.sendMessage(EssentialsGreen.prefix + "You'd healed from" + sender.getName());
				}else sender.sendMessage(EssentialsGreen.prefix + "This Player is not online!");
			}
		}
		return true;
	}
}