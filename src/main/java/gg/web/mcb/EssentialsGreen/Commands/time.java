package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class time implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.Time")){
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/time <set|add> <Time Ticks>");
				}else if(args.length > 0){
					if(args[0].equalsIgnoreCase("set")){
						if(args.length == 2){
							if(args[1].equalsIgnoreCase("day")){
								p.getWorld().setTime(1000);
							}else if(args[1].equalsIgnoreCase("night")){
								p.getWorld().setTime(13000);
							}else{
								long time = new Long(args[1]);
								p.getWorld().setTime(time);
								p.sendMessage(EssentialsGreen.prefix + "The time was " + time);
							}
						}else p.sendMessage(EssentialsGreen.prefix + "/time set <Time Ticks>");
					}else if(args[0].equalsIgnoreCase("add")){
						if(args.length == 2){
							World W = p.getWorld();
							long time = new Long(args[1]) + W.getTime();
							p.getWorld().setTime(time);
							p.sendMessage(EssentialsGreen.prefix + "The time was " + time);
						}else p.sendMessage(EssentialsGreen.prefix + "/time add <Time Ticks>");
					}else if(args[0].equalsIgnoreCase("query")){
						p.sendMessage(EssentialsGreen.prefix + "The Time is: " + p.getWorld().getTime());
					}else p.sendMessage(EssentialsGreen.prefix + "/time <set|add> <Time Ticks>");
				}
			}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		}else System.out.println(EssentialsGreen.prefix + "§4[§lError§r§4] You must be a Player!");
		return true;
	}
}