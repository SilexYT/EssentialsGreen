package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import gg.web.mcb.EssentialsGreen.API.StringAPI;

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
				}else sender.sendMessage(EssentialsGreen.prefix + "/spawnpoint [<Player|@a>] or /spawnpoint <Player|@a> <X> <Y> <Z> <World>");
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
						}else p.sendMessage(EssentialsGreen.prefix + "T§4[§lError§r§4] The player is not online");
					}
				}else sender.sendMessage(EssentialsGreen.prefix + "/spawnpoint [<Player|@a>] or /spawnpoint <Player|@a> <X> <Y> <Z> <World>");
			}else if(args.length > 4){
				if(StringAPI.isNumber(args[1]) & StringAPI.isNumber(args[2]) & StringAPI.isNumber(args[3])){
					int X = Integer.getInteger(args[1]);
					int Y = Integer.getInteger(args[2]);
					int Z = Integer.getInteger(args[3]);
					World World = Bukkit.getWorld(args[4]);
					if(World != null){
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
							}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online");
						}
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World exist not!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Write the Cordinates!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}