package me.noip.ccbluex.EssentialsGreen.Commands;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public class chat implements CommandExecutor {

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.chat")){
			if(args.length == 0 | args.length > 2){
				sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chatmanagerprefix").toString() + "\n"
						+ "§e/chat clear <Player/@a>\n"
						+ "§e/chat send <Player/@a> <Message>\n"
						+ "§e/chat mute <Player>");
			}else if(args.length > 0){
				if(args[0].equalsIgnoreCase("clear")){
					if(args.length > 2){
						if(args[1].equalsIgnoreCase("@a")){
							EssentialsGreen.getEssentialsGreenManager().getChatManager().clear((Collection<Player>)Bukkit.getOnlinePlayers());
							sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chatclearall"));
						}else if(Bukkit.getPlayer(args[1]) != null){
							Player p = Bukkit.getPlayer(args[1]);
							EssentialsGreen.getEssentialsGreenManager().getChatManager().clear(p);
							sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chatclearforplayer").toString().replace("(Player)", p.getDisplayName()));
						}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("theplayercannotfound").toString());
					}else sender.sendMessage(EssentialsGreen.prefix + "/chat clear <Player/@a>");
				}else if(args[0].equalsIgnoreCase("send")){
					if(args.length > 3){
						if(args[1].equalsIgnoreCase("@a")){
							EssentialsGreen.getEssentialsGreenManager().getChatManager().send((Collection<Player>)Bukkit.getOnlinePlayers(), ChatColor.translateAlternateColorCodes('&', args[2]));
							sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chatclearall"));
						}else if(Bukkit.getPlayer(args[1]) != null){
							Player p = Bukkit.getPlayer(args[1]);
							EssentialsGreen.getEssentialsGreenManager().getChatManager().send(p, ChatColor.translateAlternateColorCodes('&', args[2]));
							sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chatclearforplayer").toString().replace("(Player)", p.getDisplayName()));
						}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("theplayercannotfound").toString());
					}else sender.sendMessage(EssentialsGreen.prefix + "/chat send <Player/@a> <Message>");
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions").toString());
		return true;
	}
}