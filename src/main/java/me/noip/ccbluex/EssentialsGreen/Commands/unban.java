package me.noip.ccbluex.EssentialsGreen.Commands;

import java.io.File;
import java.io.IOException;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class unban implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public unban(EssentialsGreen main) {
		plugin = main;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args) {
		if(p.hasPermission("EssentialsGreen.unban")){
			if(args.length == 0){
				p.sendMessage(EssentialsGreen.prefix + "/unban <Player>");
			}else if(args.length > 0){
				OfflinePlayer Player = Bukkit.getOfflinePlayer(args[0]);
				File File = new File("plugins/EssentialsGreen/userdata/" + Player.getUniqueId().toString() + ".data");
				YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(File);
				if(File.exists()){
						UserFileYaml.set("Ban.Enable", "false");
						UserFileYaml.set("Ban.Reason", "null");
						UserFileYaml.set("Ban.Author", "null");
						UserFileYaml.set("Ban.date", "null");
						UserFileYaml.set("Ban.Ex", "null");
						UserFileYaml.set("Ban.dateSeconds", "null");
						try{
							UserFileYaml.save(File);
						}catch(IOException e){
							p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The User file can not save!");
						}
						p.sendMessage(EssentialsGreen.prefix + args[0] + " unbanned!");
				}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player has never been on the server");
			}else p.sendMessage(EssentialsGreen.prefix + "/unban <Player>");
		}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}