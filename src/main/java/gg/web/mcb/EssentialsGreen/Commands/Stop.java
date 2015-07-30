package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.StopServer")){
			Bukkit.broadcastMessage(EssentialsGreen.prefix + "§2Server Stopping By " + sender.getName());
			for(Player p : Bukkit.getOnlinePlayers()){
				p.kickPlayer(EssentialsGreen.prefix + "§2Server Stopping By " + sender.getName());
			}
			Bukkit.shutdown();
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}