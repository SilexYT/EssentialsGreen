package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.tp")){
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/tp <Target> [Target]");
				}else if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						p.teleport(target);
						p.sendMessage(EssentialsGreen.prefix + "Teleport...");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online");
				}else if(args.length > 1){
					Player p2 = Bukkit.getPlayer(args[0]);
					Player target = Bukkit.getPlayer(args[1]);
					if(!(target == null)){
						if(!(p2 == null)){
							p2.teleport(target);
							p2.sendMessage(EssentialsGreen.prefix + "Teleport...");
						}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This teleport player is not online");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The target player is not online");
				}
			}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		}else System.out.println(EssentialsGreen.prefix + "§4[§lError§r§4] You must be a Player!");
		return true;
	}
}