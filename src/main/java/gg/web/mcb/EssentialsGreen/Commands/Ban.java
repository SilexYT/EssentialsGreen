package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import gg.web.mcb.EssentialsGreen.API.StringAPI;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ban implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public ban(EssentialsGreen main) {
		plugin = main;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, final String[] args) {
		if(p.hasPermission("EssentialsGreen.ban")){
			if(args.length == 0){
				p.sendMessage(EssentialsGreen.prefix + "/ban <Player> <Reason...>");
			}else if(args.length > 1){
				String Reason = "";
				OfflinePlayer Player = Bukkit.getOfflinePlayer(args[0]);
				File File = new File("plugins/EssentialsGreen/userdata/" + Player.getUniqueId().toString() + ".data");
				YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(File);
				Reason = StringAPI.toCompleteString(args, 1).replace('&', '§');;
				if(File.exists()){
					if(!Bukkit.getOperators().contains(args[0])){
						UserFileYaml.set("Ban.Enable", "true");
						UserFileYaml.set("Ban.Reason", Reason);
						UserFileYaml.set("Ban.Author", p.getName());
						Date date = new Date();
					    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
						UserFileYaml.addDefault("Ban.date", time.format(date));
						UserFileYaml.addDefault("Ban.Ex", "never");
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null) target.kickPlayer(plugin.getConfig().getString("Ban-Prefix").replace('&', '§') + Reason.replace('&', '§'));
						try{
							UserFileYaml.save(File);
						}catch (IOException e){
							p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The User file can not save!");
						}
						p.sendMessage(EssentialsGreen.prefix + args[0] + " banned!");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You can not ban the player!");
				}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This player has never been on the server");
			}
		}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}