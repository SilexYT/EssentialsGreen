package gg.web.mcb.EssentialsGreen.Commands;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import gg.web.mcb.EssentialsGreen.API.StringAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class effect implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.effect")){
			if(args.length == 2){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					setPotionEffect(target, sender, args, 20*30, 0);
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}else if(args.length == 3){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(StringAPI.isNumber(args[2])){
						setPotionEffect(target, sender, args, 20*Integer.getInteger(args[2]), 0);
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Write a Time number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}else if(args.length > 3){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(StringAPI.isNumber(args[2])){
						if(StringAPI.isNumber(args[3])){
							if(Integer.getInteger(args[3]) != 0){
								setPotionEffect(target, sender, args, 20*Integer.getInteger(args[2]), Integer.getInteger(args[3])-1);
							}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The level number must be higher his 0!");
						}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Write a Level number!");
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Write a Time number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}else sender.sendMessage(EssentialsGreen.prefix + "/effect <Player> <Effect> [Time] [Level]");
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}

	@SuppressWarnings("deprecation")
	public void setPotionEffect(Player target, CommandSender sender, String[] args, int length, int Level){
		PotionEffectType E = PotionEffectType.getByName(args[1]);
		if(E != null){
			PotionEffect PE = new PotionEffect(E, length, Level);
			target.addPotionEffect(PE);
			sender.sendMessage(EssentialsGreen.prefix + target.getName() + " Gived " + E.getName() + " for 30 Seconds and Level 1");
		}else if(Integer.getInteger(args[1]) != null){
			E = PotionEffectType.getById(new Integer(args[1]));
			if(E != null){
				PotionEffect PE = new PotionEffect(E, length, Level);
				target.addPotionEffect(PE);
				sender.sendMessage(EssentialsGreen.prefix + target.getName() + " Gived " +  E.getName() + " for " + length + " Seconds and Level " + Level);
			}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This Effect exist not!");
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This Effect exist not!");
	}
}