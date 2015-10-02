package me.noip.ccbluex.EssentialsGreen.Commands;

import java.util.ArrayList;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.util.BukkitAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.inventivegames.nickname.Nicks;

public class nick implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public nick(EssentialsGreen main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.nick")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/nick <off/name>");
				}else if(args.length > 0){
					if(args[0].length() < 16){
						if(BukkitAPI.getPlugins().contains("NickNamer")){
							if(args[0].equalsIgnoreCase("off")){
								String name = p.getName();
								Nicks.removeNick(p.getUniqueId());
								Nicks.removeSkin(p.getUniqueId());
								p.sendMessage(EssentialsGreen.prefix + "Your name is now again §7'" + name + "'");
							}else{
								ArrayList<String> BlockNickNames = (ArrayList<String>)plugin.getConfig().getStringList("NickNames");
								if(BlockNickNames.contains(args[0])){
									String prefix = plugin.getConfig().getString("NickNamePrefix").replace('&', '§');
									Nicks.setNick(p.getUniqueId(), prefix + args[0]);
									Nicks.setSkin(p.getUniqueId(), prefix + args[0]);
									p.sendMessage(EssentialsGreen.prefix + "Your name is now §7'" + args[0] + "'");
								}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Name is on the blacklist!");
							}
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Install the Plugin NickNamer from Spigotmc.org");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The name must be under 16");
				}
			}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youmustplayer"));
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}