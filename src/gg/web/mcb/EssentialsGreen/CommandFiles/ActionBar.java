package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.API.ActionBarAPI;
import gg.web.mcb.EssentialsGreen.API.JavaAPI;
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
				sender.sendMessage(EssentialsGreen.prefix + "/actionbar <Player|@a> <Message>");
			}else if(args.length > 1){
				String Text = "";
				for(int i = 1; args.length > i; i++){
					if(Text == args[i]){
						Text = Text + " " + args[i];
					}else{
						Text = args[i];
					}
				}
				if(args[0].equalsIgnoreCase("@a")){
					for(Player p : Bukkit.getOnlinePlayers()){
						ActionBarAPI.sendActionBar(p, args[1]);
					}
				}else{
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						ActionBarAPI.sendActionBar(target, args[1]);
						sender.sendMessage(EssentialsGreen.prefix + "Message sent to " + args[0]);
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This target player is not online");
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}