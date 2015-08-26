package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class setspawn implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public setspawn(EssentialsGreen main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssntialsGreen.setspawn")){
				Location loc = p.getLocation();
				YamlConfiguration SY = plugin.SpawnYaml;
				SY.set("Spawn.Enable", true);
				SY.set("Spawn.Location.X", loc.getX());
				SY.set("Spawn.Location.Y", loc.getY());
				SY.set("Spawn.Location.Z", loc.getZ());
				SY.set("Spawn.Location.Yaw", loc.getYaw());
				SY.set("Spawn.Location.Pitch", loc.getPitch());
				SY.set("Spawn.Location.World", loc.getWorld().getName());
				p.sendMessage(EssentialsGreen.prefix + "Spawn Location set!");
				try{
					SY.save(plugin.SpawnF);
				}catch (IOException e) {
					e.printStackTrace();
				}
			}else p.sendMessage(EssentialsGreen.prefix + "�4[�lError�r�4] You do not have the required permissions");
		}else System.out.println(EssentialsGreen.prefix + "�4[�lError�r�4] You must be a Player!");
		return true;
	}
}