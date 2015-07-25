package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.API.ActionBarAPI;
import gg.web.mcb.EssentialsGreen.API.JavaAPI;
import gg.web.mcb.EssentialsGreen.API.StringAPI;
import gg.web.mcb.EssentialsGreen.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class actionbar extends JavaAPI implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.actionbar")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/actionbar <Player|@a> <Message...>");
			}else if(args.length > 1){
				String Text = StringAPI.toCompleteString(args, 1).replace('&', '§');
				if(args[0].equalsIgnoreCase("@a")){
					for(Player p : Bukkit.getOnlinePlayers()){
						ActionBarAPI.sendActionBar(p, Text);
					}
				}else{
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						ActionBarAPI.sendActionBar(target, Text);
						sender.sendMessage(EssentialsGreen.prefix + "Message sent to " + args[0]);
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This target player is not online");
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}