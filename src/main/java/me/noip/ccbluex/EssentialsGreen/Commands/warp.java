package me.noip.ccbluex.EssentialsGreen.Commands;

import java.io.File;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.util.Results;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class warp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.Warp")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + " [Warp Manager]\n"
							+ "/warp <Warp>\n"
							+ "/warp add <Name>\n"
							+ "/warp remove <Name>\n"
							+ "/warp list\n");
				}else if(args.length > 0){
					if(args[0].equalsIgnoreCase("add")){
						if(p.hasPermission("EssentialsGreen.Warp.*") | p.hasPermission("EssentialsGreen.Warp.add")){
							if(args.length > 1){
								if(!EssentialsGreen.getEssentialsGreenManager().getWarpManager().contains(args[1])){
									EssentialsGreen.getEssentialsGreenManager().getWarpManager().addWarp(args[1], p.getLocation());
									p.sendMessage(EssentialsGreen.prefix + "Warp add!");
								}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This Warp already exists!");
							}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Please write a WarpName");
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the permissions for the command!");
					}else if(args[0].equalsIgnoreCase("remove")){
						if(p.hasPermission("EssentialsGreen.Warp.*") | p.hasPermission("EssentialsGreen.Warp.remove")){
							if(!(EssentialsGreen.getEssentialsGreenManager().getWarpManager().removeWarp(args[1]) == Results.Success)){
								p.sendMessage(EssentialsGreen.prefix + "Warp deleted!");
							}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This Warp exists not!");
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the permissions for the command!");
					}else if(args[0].equalsIgnoreCase("list")){
						if(p.hasPermission("EssentialsGreen.Warp.*") | p.hasPermission("EssentialsGreen.Warp.list")){
							p.sendMessage(EssentialsGreen.prefix + "Warp List");
							File WarpFile = new File("plugins/EssentialsGreen/Warp");
							File[] Files = WarpFile.listFiles();
							for(int i = 0; i < Files.length; i++){
								p.sendMessage("§e" + YamlConfiguration.loadConfiguration(Files[i]).getString("Name"));
							}
							p.sendMessage("§3" + Files.length + " warps exist!");
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the permissions for the command!");
					}else{
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + args[0] + ".yml");
						if(WarpFile.exists()){
							if(p.hasPermission("EssentialsGreen.Warp." + args[0]) | p.hasPermission("EssentialsGreen.Warp.*")){
								YamlConfiguration File = YamlConfiguration.loadConfiguration(WarpFile);
								World world = Bukkit.getWorld(File.getString("Location.World"));
								if(world != null){
									Location SpawnLoc = new Location(world, File.getDouble("Location.X"), File.getDouble("Location.Y"), File.getDouble("Location.Z"), new Float(File.getString("Location.Yaw")), new Float(File.getString("Location.Pitch")));
									p.teleport(SpawnLoc);
									p.sendMessage(EssentialsGreen.prefix + "Teleport...");
								}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World exist not more!");
							}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the permissions for the Warp!");
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Warp exist not more!");
					}
				}
			}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You must be a Player!");
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}