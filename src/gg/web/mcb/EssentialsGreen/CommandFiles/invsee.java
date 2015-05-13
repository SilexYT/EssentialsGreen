package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class invsee implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.invsee")){
				if(args.length == 0){
					p.sendMessage(main.prefix + "/invsee <Player>");
				}else if(args.length > 0){
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						p.openInventory(target.getInventory());
					}else p.sendMessage(main.prefix + "This target player is not online");
				}
			}else p.sendMessage(main.prefix + "You do not have the required permissions");
		}else sender.sendMessage(main.prefix + "You must be a Player");
		return false;
	}
}
