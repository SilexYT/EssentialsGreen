package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class toggledownfall implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(args.length == 0){
			if(sender instanceof Player){
				Player p = (Player)sender;
				World W = p.getWorld();
				if(W.getWeatherDuration() == 0){
					W.setWeatherDuration(1);
					p.sendMessage(EssentialsGreen.prefix + "The Weather is now Rain");
				}else if(W.getWeatherDuration() == 1){
					W.setWeatherDuration(0);
					p.sendMessage(EssentialsGreen.prefix + "The Weather is now Sun");
				}
				/* This Command is not Finish!
				 * Commands is soon avaible!
				 */
			}else sender.sendMessage(EssentialsGreen.prefix + "/toggledownfall <World>");
		}
		return true;
	}
}