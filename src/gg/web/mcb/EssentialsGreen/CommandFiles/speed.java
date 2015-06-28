package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class speed implements CommandExecutor {

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.speed")){
			if(args.length == 1){
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(new Integer(args[0]) != null){
						float i = new Float(args[0])/10;
						if(new Integer(args[0]) < 11){
							if(p.isFlying()){
								p.setFlySpeed(i);
								p.sendMessage(EssentialsGreen.prefix + "Fly Speed changed!");
							}else{
								p.setWalkSpeed(i);
								p.sendMessage(EssentialsGreen.prefix + "Walk Speed changed!");
							}
						}else p.sendMessage(EssentialsGreen.prefix + "Please Write a number under 11");
					}else p.sendMessage(EssentialsGreen.prefix + "This is no number");
				}else sender.sendMessage(EssentialsGreen.prefix + "You must be a Player");
			}else{
				if(args.length > 1){
					Player target = Bukkit.getPlayer(args[1]);
					if(!(target == null)){
						if(new Integer(args[0]) != null){
							float i = new Float(args[0])/10;
							if(new Integer(args[0]) < 11){
								if(target.isFlying()){
									target.setFlySpeed(i);
									target.sendMessage(EssentialsGreen.prefix + "Fly Speed changed!");
									sender.sendMessage(EssentialsGreen.prefix + "Fly Speed changed from " + args[1] + "!");
								}else{
									target.setWalkSpeed(i);
									target.sendMessage(EssentialsGreen.prefix + "Walk Speed changed!");
									sender.sendMessage(EssentialsGreen.prefix + "Walk Speed changed from " + args[1] + "!");
								}
							}else sender.sendMessage(EssentialsGreen.prefix + "Please Write a number under 11");
						}else sender.sendMessage(EssentialsGreen.prefix + "This is no number");
					}else sender.sendMessage(EssentialsGreen.prefix + "This player is not online");
				}else sender.sendMessage(EssentialsGreen.prefix + "/speed <number> [Player]");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}