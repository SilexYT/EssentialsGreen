package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setworldspawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.setworldspawn")){
				World W = p.getWorld();
				Location loc = p.getLocation();
				W.setSpawnLocation((int)loc.getX(), (int)loc.getY(), (int)loc.getZ());
				p.sendMessage(EssentialsGreen.prefix + "Set World Spawn completed!");
			}else p.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		}else sender.sendMessage(EssentialsGreen.prefix + "You must be a Player");
		return true;
	}
}