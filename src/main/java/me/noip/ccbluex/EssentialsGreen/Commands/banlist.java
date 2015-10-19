package me.noip.ccbluex.EssentialsGreen.Commands;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public class banlist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.Banlist")){
			File F = new File("plugins/EssentialsGreen/users");
			File[] list = F.listFiles();
			sender.sendMessage(EssentialsGreen.prefix + "[Banlist]");
			for(int i = 0; i < list.length; i++){
				YamlConfiguration Player = YamlConfiguration.loadConfiguration(list[i]);
				if(Player.getString("Ban.Enable") != null){
					if(Player.getString("Ban.Enable").equalsIgnoreCase("true")){
						sender.sendMessage("§2§l" + Player.getString("Username") + " §r§a| §7Reason §a: §3§l" + Player.getString("Ban.Reason"));
					}
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}