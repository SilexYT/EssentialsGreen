package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.ApiFiles.ActionBarApi;
import gg.web.mcb.EssentialsGreen.MainPackage.main;
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
				sender.sendMessage(main.prefix + "/actionbar <Player> <Message>");
			}else if(args.length == 1){
				sender.sendMessage(main.prefix + "/actionbar " + args[0] + " <Message>");
			}else if(args.length == 2){
				Player target = Bukkit.getPlayer(args[0]);
				if(!(target == null)){
					ActionBarApi.sendActionBar(target, args[1]);
					sender.sendMessage(main.prefix + "Message sent to " + args[0]);
				}else sender.sendMessage(main.prefix + "This target player is not online");
			}else sender.sendMessage(main.prefix + "/actionbar <Player> <Message>");
		}else sender.sendMessage(main.prefix + "You do not have the required permissions");
		return true;
	}
}