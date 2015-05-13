package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.main;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.Time")){
				if(args.length == 0){
					p.sendMessage(main.prefix + "/time <set|add> <Time Ticks>");
				}else if(args.length > 0){
					if(args[0].equalsIgnoreCase("set")){
						if(args.length == 2){
							long time = new Long(args[1]);
							TimeSet(time, p);
						}else p.sendMessage(main.prefix + "/time set <Time Ticks>");
					}else if(args[0].equalsIgnoreCase("add")){
						if(args.length == 2){
							World W = p.getWorld();
							long time = new Long(args[1]) + W.getTime();
							TimeSet(time, p);
						}else p.sendMessage(main.prefix + "/time add <Time Ticks>");
					}else p.sendMessage(main.prefix + "/time <set|add> <Time Ticks>");
				}
			}
		}else System.out.println("[EssentialsGreen] You must be a Player!");
		return true;
	}
	
	public void TimeSet(long Time, Player p){
		p.getWorld().setTime(Time);
		p.sendMessage(main.prefix + "The time was " + Time);
	}
}
