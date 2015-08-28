package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public spawn(EssentialsGreen main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(args.length == 0){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("EssentialsGreen.spawn")){
					YamlConfiguration SY = plugin.SpawnYaml;
					if(SY.getString("Spawn.Enable") != null){
						World world = Bukkit.getWorld(SY.getString("Spawn.Location.World"));
						if(world != null){
							Location SpawnLoc = new Location(world, SY.getDouble("Spawn.Location.X"), SY.getDouble("Spawn.Location.Y"), SY.getDouble("Spawn.Location.Z"), new Float(SY.getString("Spawn.Location.Yaw")), new Float(SY.getString("Spawn.Location.Pitch")));
							p.teleport(SpawnLoc);
							p.sendMessage(EssentialsGreen.prefix + "Teleport...");
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World exist not more!");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] No Spawn Found");
				}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
			}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You must be a Player!");
		}else if(args.length > 0){
			if(sender.hasPermission("EssentialsGreen.spawn.other")){
				YamlConfiguration SY = plugin.SpawnYaml;
				if(SY.getString("Spawn.Enable") != null){
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						World world = Bukkit.getWorld(SY.getString("Spawn.Location.World"));
						if(world != null){
							Location SpawnLoc = new Location(world, SY.getDouble("Spawn.Location.X"), SY.getDouble("Spawn.Location.Y"), SY.getDouble("Spawn.Location.Z"), new Float(SY.getString("Spawn.Location.Yaw")), new Float(SY.getString("Spawn.Location.Pitch")));
							target.teleport(SpawnLoc);
							target.sendMessage(EssentialsGreen.prefix + "Spawn Teleport By " + sender.getName());
						}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The World exist not more!");
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] No Spawn Found");
			}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4]You do not have the required permissions");
		}
		return true;
	}
}