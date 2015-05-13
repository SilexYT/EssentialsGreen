package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.ApiFiles.NumberManager;
import gg.web.mcb.EssentialsGreen.MainPackage.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class speed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.speed")){
			if(args.length == 1){
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(NumberManager.IsStringint(args[0])){
						float i = new Float(args[0])/10;
						if(!(i > 10)){
							if(p.isFlying()){
								p.setFlySpeed(i);
								p.sendMessage(main.prefix + "Fly Speed changed!");
							}else{
								p.setWalkSpeed(i);
								p.sendMessage(main.prefix + "Walk Speed changed!");
							}
						}else p.sendMessage(main.prefix + "Please Write a number under 11");
					}else p.sendMessage(main.prefix + "This is no number");
				}else sender.sendMessage(main.prefix + "You must be a Player");
			}else{
				if(args.length > 1){
					Player target = Bukkit.getPlayer(args[1]);
					if(!(target == null)){
						if(NumberManager.IsStringint(args[0])){
							float i = new Float(args[0])/10;
							if(!(i > 10)){
								if(target.isFlying()){
									target.setFlySpeed(i);
									target.sendMessage(main.prefix + "Fly Speed changed!");
									sender.sendMessage(main.prefix + "Fly Speed changed from " + args[1] + "!");
								}else{
									target.setWalkSpeed(i);
									target.sendMessage(main.prefix + "Walk Speed changed!");
									sender.sendMessage(main.prefix + "Walk Speed changed from " + args[1] + "!");
								}
							}else sender.sendMessage(main.prefix + "Please Write a number under 11");
						}else sender.sendMessage(main.prefix + "This is no number");
					}else sender.sendMessage(main.prefix + "This player is not online");
				}else sender.sendMessage(main.prefix + "/speed <number> [Player]");
			}
		}else sender.sendMessage(main.prefix + "You do not have the required permissions");
		return true;
	}

}
