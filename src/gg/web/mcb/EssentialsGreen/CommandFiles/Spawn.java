package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
	
	main plugin;
	
	public Spawn(main main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.spawn")){
				YamlConfiguration SY = plugin.SpawnYaml;
				if(SY.getString("Spawn.Location.World") != null){
					Location SpawnLoc = new Location(Bukkit.getWorld(SY.getString("Spawn.Location.World")), SY.getDouble("Spawn.Location.X"), SY.getDouble("Spawn.Location.Y"), SY.getDouble("Spawn.Location.Z"));
					p.teleport(SpawnLoc);
					p.sendMessage(main.prefix + "Teleport...");
				}else p.sendMessage(main.prefix + "No Spawn Found");
			}else p.sendMessage(main.prefix + "You do not have the required permissions");
		}else{
			if(args.length == 0){
				sender.sendMessage(main.prefix + "/Spawn <Player>");
			}else if(args.length > 0){
				YamlConfiguration SY = plugin.SpawnYaml;
				if(SY.getString("Spawn.Location.World") != null){
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						Location SpawnLoc = new Location(Bukkit.getWorld(SY.getString("Spawn.Location.World")), SY.getDouble("Spawn.Location.X"), SY.getDouble("Spawn.Location.Y"), SY.getDouble("Spawn.Location.Z"));
						target.teleport(SpawnLoc);
						target.sendMessage(main.prefix + "Spawn Teleport By Console");
					}
				}else sender.sendMessage(main.prefix + "No Spawn Found");
			}
		}
		return true;
	}

}
