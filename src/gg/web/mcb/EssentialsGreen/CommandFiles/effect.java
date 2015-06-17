package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings({ "deprecation", "unused" })
public class effect implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.effect")){
			if(args.length == 3){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					setPotionEffect(target, sender, args, 20*30, 1);
				}else sender.sendMessage(EssentialsGreen.prefix + "The Player is not online!");
			}else if(args.length == 4){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(new Integer(args[2]) != null){
						setPotionEffect(target, sender, args, 20*new Integer(args[2]), 1);
					}else sender.sendMessage("Please Write a Time number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "The Player is not online!");
			}else if(args.length > 4){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(new Integer(args[2]) != null){
						if(new Integer(args[3]) != null){
							setPotionEffect(target, sender, args, 20*new Integer(args[2]), new Integer(args[3]));
						}else sender.sendMessage("Please Write a Level number!");
					}else sender.sendMessage("Please Write a Time number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "The Player is not online!");
			}else sender.sendMessage(EssentialsGreen.prefix + "/effect <Player> <Effect> [Time] [Level]");
		}
		return true;
	}
	
	public void setPotionEffect(Player target, CommandSender sender, String[] args, int length, int Level){
		PotionEffectType E = PotionEffectType.getByName(args[1]);
		if(E != null){
			PotionEffect PE = new PotionEffect(E, length, Level);
			target.addPotionEffect(PE);
			sender.sendMessage(EssentialsGreen.prefix + target.getName() + " Gived " +  E + " for 30 Seconds and Level 1");
		}else if(new Integer(args[1]) != null){
			E = PotionEffectType.getById(new Integer(args[1]));
			if(E != null){
				PotionEffect PE = new PotionEffect(E, length, Level);
				target.addPotionEffect(PE);
				sender.sendMessage(EssentialsGreen.prefix + target.getName() + " Gived " +  E + " for " + length + " Seconds and Level " + Level);
			}else sender.sendMessage(EssentialsGreen.prefix + "This Effect exist not!");
		}else sender.sendMessage(EssentialsGreen.prefix + "This Effect exist not!");
	}
}