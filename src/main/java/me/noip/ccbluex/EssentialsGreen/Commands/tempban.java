package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.util.StringAPI;

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

public class tempban implements CommandExecutor {

	EssentialsGreen plugin;
	
	public tempban(EssentialsGreen main) {
		plugin = main;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.ban")){
			if(args.length < 3){
				sender.sendMessage(EssentialsGreen.prefix + "/tempban <Player> <Time in Seconds> <Reason...>");
			}else{
				String Reason = "";
				OfflinePlayer Player = Bukkit.getOfflinePlayer(args[0]);
				File File = new File("plugins/EssentialsGreen/userdata/" + Player.getUniqueId().toString() + ".data");
				YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(File);
				Reason = StringAPI.toCompleteString(args, 2).replace('&', '§');
				if(File.exists()){
					if(!Bukkit.getOperators().contains(args[0])){
						if(StringAPI.isNumber(args[1])){
							UserFileYaml.set("Ban.Enable", "true");
							UserFileYaml.set("Ban.Type", "TempBan");
							UserFileYaml.set("Ban.Reason", Reason);
							UserFileYaml.set("Ban.Author", sender.getName());
							Date date = new Date();
							SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
							UserFileYaml.set("Ban.date", time.format(date));
							UserFileYaml.set("Ban.Ex", Integer.parseInt(args[1]));
							UserFileYaml.set("Ban.dateSeconds", System.currentTimeMillis()/1000L);
							Player target = Bukkit.getPlayer(args[0]);
							if(target != null){
								target.kickPlayer((plugin.getConfig().getString("Ban-Message") + "\n§fAuthor: §e" + UserFileYaml.getString("Ban.Author") + " §fDate: §e" + UserFileYaml.getString("Ban.date") + "\n§fExpires in §e" + UserFileYaml.getString("Ban.Ex") + " §fSeconds" + "\n§fReason: §7" + UserFileYaml.getString("Ban.Reason")).replace('&', '§'));
							}
							try{
								UserFileYaml.save(File);
							}catch (IOException e){
								sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The User file can not save!");
							}
							sender.sendMessage(EssentialsGreen.prefix + args[0] + " banned!");
						}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Seconds is not a number!");
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You can not ban the player!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This player has never been on the server");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}