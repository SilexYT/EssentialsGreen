package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class vanish implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.vanish")){
			if(args.length < 1){
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(!p.getActivePotionEffects().contains(PotionEffectType.INVISIBILITY)){
						PotionEffect PE = new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1);
						p.addPotionEffect(PE);
						p.sendMessage(EssentialsGreen.prefix + "Vanish is enable!");
						p.sendMessage("§4§lWARNING: §eItems in the hand is not invisibility §3and §eArmor is not invisibility!");
					}else{
						p.removePotionEffect(PotionEffectType.INVISIBILITY);
						p.sendMessage(EssentialsGreen.prefix + "Vanish is disable!");
					}
				}else sender.sendMessage(EssentialsGreen.prefix + "/vanish <Player>");
			}else{
				Player p = Bukkit.getPlayer(args[0]);
				if(p != null){
					if(!p.getActivePotionEffects().contains(PotionEffectType.INVISIBILITY)){
						PotionEffect PE = new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1);
						p.addPotionEffect(PE);
						p.sendMessage(EssentialsGreen.prefix + "You Vanish is enable!");
						p.sendMessage("§4§lWARNING: §eItems in the hand is not invisibility §3and §eArmor is not invisibility!");
						sender.sendMessage(EssentialsGreen.prefix + "Vanish is enable at " + p.getName() + "!");
					}else{
						p.removePotionEffect(PotionEffectType.INVISIBILITY);
						p.sendMessage(EssentialsGreen.prefix + "You Vanish is disable!");
						sender.sendMessage(EssentialsGreen.prefix + "Vanish is disable at " + p.getName() + "!");
					}
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The Player is not online!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You do not have the required permissions");
		return true;
	}
}