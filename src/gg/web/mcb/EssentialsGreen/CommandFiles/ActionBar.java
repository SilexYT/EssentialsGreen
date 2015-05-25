package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.ApiFiles.ActionBarApi;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ActionBar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.actionbar")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/actionbar <Player|@a> <Message>");
			}else if(args.length > 1){
				if(args[0].equalsIgnoreCase("@a")){
					for(Player p : Bukkit.getOnlinePlayers()){
						ActionBarApi.sendActionBar(p, args[1]);
					}
				}else{
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						ActionBarApi.sendActionBar(target, args[1]);
						sender.sendMessage(EssentialsGreen.prefix + "Message sent to " + args[0]);
					}else sender.sendMessage(EssentialsGreen.prefix + "This target player is not online");
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}