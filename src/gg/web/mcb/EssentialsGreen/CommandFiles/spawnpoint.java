package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawnpoint implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.spawnpoint")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player p = (Player)sender;
					p.setBedSpawnLocation(p.getLocation());
					p.sendMessage(EssentialsGreen.prefix + "Spawn Location set!");
				}else sender.sendMessage(EssentialsGreen.prefix + "/spawnpoint <Player|@a> <X> <Y> <Z> <World>");
			}else if(args.length == 1){
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(args[0].equalsIgnoreCase("@a")){
						for(Player target : Bukkit.getOnlinePlayers()){
							target.setBedSpawnLocation(p.getLocation());
						}
						p.sendMessage(EssentialsGreen.prefix + "Spawn Location set from All Players!");
					}else{
						Player target = Bukkit.getPlayer(args[0]);
						if(!(target == null)){
							target.setBedSpawnLocation(p.getLocation());
							p.sendMessage(EssentialsGreen.prefix + "Spawn Location set from " + args[0] + "!");
						}else p.sendMessage(EssentialsGreen.prefix + "This player is not online");
					}
				}else sender.sendMessage(EssentialsGreen.prefix + "/spawnpoint <Player|@a> <X> <Y> <Z> <World>");
			}else if(args.length > 5){
				if(new Integer(args[1]) != null & new Integer(args[2]) != null & new Integer(args[3]) != null){
					int X = new Integer(args[1]);
					int Y = new Integer(args[2]);
					int Z = new Integer(args[3]);
					World World = Bukkit.getWorld(args[4]);
					Location loc = new Location(World, X, Y, Z);
					if(args[0].equalsIgnoreCase("@a")){
						for(Player target : Bukkit.getOnlinePlayers()){
							target.setBedSpawnLocation(loc);
						}
						sender.sendMessage(EssentialsGreen.prefix + "Spawn Location set from All Players to " + X + " " + Y + " " + Z + "!");
					}else{
						Player target = Bukkit.getPlayer(args[0]);
						if(!(target == null)){
							target.setBedSpawnLocation(loc);
							sender.sendMessage(EssentialsGreen.prefix + "Spawn Location set from " + args[0] + "!");
						}else sender.sendMessage(EssentialsGreen.prefix + "This player is not online");
					}
				}else sender.sendMessage(EssentialsGreen.prefix + "Please Write Cordinates!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}