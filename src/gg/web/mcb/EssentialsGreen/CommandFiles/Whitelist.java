package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class whitelist implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.Whitelist")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + " §4[§2Whitelist Manager§4]§e\n"
						+ "/whitelist add <Name>\n"
						+ "/whitelist remove <Name>\n"
						+ "/whitelist list\n"
						+ "/whitelist on\n"
						+ "/whitelist off\n"
						+ "/whitelist reload");
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("add")){
					sender.sendMessage(EssentialsGreen.prefix + "/whitelist add <Name>");
				}else if(args[0].equalsIgnoreCase("remove")){
					sender.sendMessage(EssentialsGreen.prefix + "/whitelist remove <Name>");
				}else if(args[0].equalsIgnoreCase("list")){
					int players = 0;
					for(OfflinePlayer p : Bukkit.getWhitelistedPlayers()){
						sender.sendMessage("§e" + p.getName());
						players++;
					}
					sender.sendMessage("§eWhitelisted players: " + players);
				}else if(args[0].equalsIgnoreCase("on")){
					Bukkit.setWhitelist(true);
					sender.sendMessage(EssentialsGreen.prefix + "Whitelist is true!");
				}else if(args[0].equalsIgnoreCase("off")){
					Bukkit.setWhitelist(false);
					sender.sendMessage(EssentialsGreen.prefix + "Whitelist is false!");
				}else if(args[0].equalsIgnoreCase("reload")){
					Bukkit.reloadWhitelist();
					sender.sendMessage(EssentialsGreen.prefix + "Whitelist reloaded!");
				}
			}else if(args.length > 1){
				if(args[0].equalsIgnoreCase("add")){
					OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
					p.setWhitelisted(true);
					sender.sendMessage(EssentialsGreen.prefix + "Player add!");
				}else if(args[0].equalsIgnoreCase("remove")){
					OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
					p.setWhitelisted(false);
					sender.sendMessage(EssentialsGreen.prefix + "Player remove!");
				}else if(args[0].equalsIgnoreCase("list")){
					int players = 0;
					for(OfflinePlayer p : Bukkit.getWhitelistedPlayers()){
						sender.sendMessage("§e" + p.getName());
						players++;
					}
					sender.sendMessage("§eWhitelisted players: " + players);
				}else if(args[0].equalsIgnoreCase("on")){
					Bukkit.setWhitelist(true);
					sender.sendMessage(EssentialsGreen.prefix + "Whitelist is true!");
				}else if(args[0].equalsIgnoreCase("off")){
					Bukkit.setWhitelist(false);
					sender.sendMessage(EssentialsGreen.prefix + "Whitelist is false!");
				}else if(args[0].equalsIgnoreCase("reload")){
					Bukkit.reloadWhitelist();
					sender.sendMessage(EssentialsGreen.prefix + "Whitelist reloaded!");
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}