package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import gg.web.mcb.EssentialsGreen.API.StringAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class msg implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.msg")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/msg <Player> <Message...>");
			}else if(args.length > 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(!(target == null)){
					String Text = StringAPI.toCompleteString(args, 1).replace('&', '§');
					sender.sendMessage(EssentialsGreen.prefix + "§2[MSG]§f Message sent!");
					target.sendMessage(EssentialsGreen.prefix + "§2[MSG]§f " + sender.getName() + " --> " + Text);
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}