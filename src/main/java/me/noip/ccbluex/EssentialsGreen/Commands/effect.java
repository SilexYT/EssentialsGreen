package me.noip.ccbluex.EssentialsGreen.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.APIs.StringAPI;

public class effect implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.effect")){
			if(args.length == 2){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(!args[1].equalsIgnoreCase("clear")){
						setPotionEffect(target, sender, args, 20*30, 0);
					}else{
						for(PotionEffect pe : target.getActivePotionEffects()){
							target.removePotionEffect(pe.getType());
						}
						sender.sendMessage(EssentialsGreen.prefix + "All effect is cleared from the player!");
					}
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}else if(args.length == 3){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(StringAPI.isInteger(args[2])){
						setPotionEffect(target, sender, args, 20*Integer.parseInt(args[2]), 0);
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Write a Time number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}else if(args.length > 3){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					if(StringAPI.isInteger(args[2])){
						if(StringAPI.isInteger(args[3])){
							if(Integer.parseInt(args[3]) != 0){
								setPotionEffect(target, sender, args, 20*Integer.parseInt(args[2]), Integer.parseInt(args[3])-1);
							}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The level number must be higher his 0!");
						}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Write a Level number!");
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Write a Time number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}else sender.sendMessage(EssentialsGreen.prefix + "/effect <Player> <Effect/clear> [Time] [Level]");
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}

	@SuppressWarnings("deprecation")
	public void setPotionEffect(Player target, CommandSender sender, String[] args, int length, int Level){
		PotionEffectType E = PotionEffectType.getByName(args[1]);
		if(E != null){
			PotionEffect PE = new PotionEffect(E, length, Level);
			target.addPotionEffect(PE);
			sender.sendMessage(EssentialsGreen.prefix + target.getName() + " Gived " + E.getName());
		}else if(Integer.getInteger(args[1]) != null){
			E = PotionEffectType.getById(new Integer(args[1]));
			if(E != null){
				PotionEffect PE = new PotionEffect(E, length, Level);
				target.addPotionEffect(PE);
				sender.sendMessage(EssentialsGreen.prefix + target.getName() + " Gived " +  E.getName());
			}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This Effect exist not!");
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] This Effect exist not!");
	}
}