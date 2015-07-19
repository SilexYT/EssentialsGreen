package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class banlist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.Banlist")){
			File F = new File("plugins/EssentialsGreen/userdata");
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
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}