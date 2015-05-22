package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Info implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args){
		if(sender.hasPermission("EssentialsGreen.Info")){
			String BV = Bukkit.getVersion();
			int P = EssentialsGreen.OnlinePlayers.size();
			int MP = Bukkit.getMaxPlayers();
			String IP = Bukkit.getIp();
			String Motd = Bukkit.getMotd();
			boolean OnlineMode = Bukkit.getOnlineMode();
			sender.sendMessage(EssentialsGreen.prefix + " [Info]\nThere are " + P + "/" + MP + "\nIP : " + IP + "\nMOTD : " + Motd + "\nOnlineMode : " + OnlineMode + "\nVersion : " + BV);
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}

}
