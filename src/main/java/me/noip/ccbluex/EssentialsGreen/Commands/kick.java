package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.APIs.StringAPI;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kick implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args) {
			if(p.hasPermission("EssentialsGreen.kick")){
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/kick <Player> <Reason...>");
				}else{
					//Reason
					String Reason;
					if(args.length == 1){
						Reason = "§7You kicked from this Server!";
					}else{
						Reason = StringAPI.toCompleteString(args, 1);
					}
					//Kick
					Player kickPlayer = Bukkit.getPlayer(args[0]);
					if(!(kickPlayer == null)){
						kickPlayer.kickPlayer(ChatColor.translateAlternateColorCodes('&', EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Kick-Prefix").toString() + " " + Reason));
						Bukkit.broadcastMessage(EssentialsGreen.prefix + args[0] + " was kicked, Reason : " + Reason);
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online");
				}
			}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}