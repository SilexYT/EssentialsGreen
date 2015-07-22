package gg.web.mcb.EssentialsGreen.CommandFiles;

import java.util.ArrayList;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.nick")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/nick <Name|off>");
				}else if(args.length > 0){
					if(args[0].length() < 16){
						if(args[0].equalsIgnoreCase("off")){
							String name = p.getName();
							p.setPlayerListName(name);
							Nicks.removeNick(p.getUniqueId());
							Nicks.removeSkin(p.getUniqueId());
							p.sendMessage(EssentialsGreen.prefix + "Your name is now again " + name);
						}else{
							boolean block = false;
							ArrayList<String> BlockNickNames = (ArrayList<String>)plugin.getConfig().get("NickNames");
							for(int i = 0; i < BlockNickNames.size(); i++){
								if(BlockNickNames.get(i).equalsIgnoreCase(args[0])){
									block = true;
								}
							}
							if(block == false){
								String prefix = plugin.getConfig().getString("NickNamePrefix").replace('&', '§');
								Nicks.setNick(p.getUniqueId(), prefix + args[0]);
								Nicks.setSkin(p.getUniqueId(), prefix + args[0]);
								p.setPlayerListName(prefix + args[0]);
								p.sendMessage(EssentialsGreen.prefix + "Your name is now " + args[0]);
							}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Name is on the blacklist!");
						}
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The name must be under 16");
				}
			}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You must be a Player");
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}