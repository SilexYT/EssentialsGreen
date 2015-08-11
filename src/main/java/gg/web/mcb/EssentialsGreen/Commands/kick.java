package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import gg.web.mcb.EssentialsGreen.util.StringAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kick implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public kick(EssentialsGreen main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args) {
			if(p.hasPermission("EssentialsGreen.kick")){
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/kick <Player> <Reason...>");
				}else if(args.length > 1){
					String Reason = StringAPI.toCompleteString(args, 1).replace('&', '§');
					Player kickPlayer = Bukkit.getPlayer(args[0]);
					if(!(kickPlayer == null)){
						kickPlayer.kickPlayer(plugin.getConfig().getString("Kick-Prefix").replace('&', '§') + Reason);
						Bukkit.broadcastMessage(EssentialsGreen.prefix + args[0] + " was kicked, Reason : " + Reason);
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online");
				}
			}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}