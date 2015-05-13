package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class kill implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.kill")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/kill <Player|@a|@e <World>> [PlayerName]");
			}else if(args.length > 0){
				if(args[0].equalsIgnoreCase("@a")){
					Player[] p = Bukkit.getOnlinePlayers();
					for(int i = 0; p.length > i; i++){
						p[i].setHealth(0.0);
					}
				}else if(args[0].equalsIgnoreCase("@e")){
					if(args.length > 1){
						Player[] p = Bukkit.getOnlinePlayers();
						for(int i = 0; p.length > i; i++){
							p[i].setHealth(0.0);
						}
						
						List<Entity> e = Bukkit.getWorld(args[1]).getEntities();
						for(int i = 0; e.size() > i; i++){
							Entity E = e.get(i);
							if(!E.getType().equals(EntityType.PLAYER)){
								E.remove();	
							}
						}
					}else sender.sendMessage(EssentialsGreen.prefix + "Please provide a World");
				}else if(args[0].equalsIgnoreCase("Player")){
					if(args.length > 1){
						Player target = Bukkit.getPlayer(args[1]);
						if(!(target == null)){
							target.setHealth(0.0);
						}else sender.sendMessage(EssentialsGreen.prefix + "This player is not online");
					}else sender.sendMessage(EssentialsGreen.prefix + "Please provide a Name");
				}else sender.sendMessage(EssentialsGreen.prefix + "/kill <Player|@a|@e <World>> [PlayerName]");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}