package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class nick implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public nick(EssentialsGreen main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.nick")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/nick <off/name>");
				}else if(args.length > 0){
					if(args[0].length() < 16){
						if(args[0].equalsIgnoreCase("off")){
							String name = p.getName();
							p.setDisplayName(p.getName());
							p.setPlayerListName(p.getName());
							p.sendMessage(EssentialsGreen.prefix + "Your name is now again §7'" + name + "'");
						}else{
							String prefix = plugin.getConfig().getString("NickNamePrefix").replace('&', '§');
							p.setDisplayName(prefix + args[0]);
							p.setPlayerListName(prefix + args[0]);
							p.sendMessage(EssentialsGreen.prefix + "Your name is now §7'" + args[0] + "'");
						}
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The name must be under 16");
				}
			}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youmustplayer"));
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}