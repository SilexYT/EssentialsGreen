package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.util.StringAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class xp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.xp")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/xp <set|add> <xp> [Player]");
			}else if(args.length == 1){
				sender.sendMessage(EssentialsGreen.prefix + "/xp <set|add> <xp> [Player]");
			}else if(args.length == 2){
				if(sender instanceof Player){
					Player p = (Player)sender;
					String number = args[1];
					if(StringAPI.isInteger(number)){
						if(args[0].equalsIgnoreCase("set")){
							p.setLevel(new Integer(number));
							p.sendMessage(EssentialsGreen.prefix + "The xp set succesfully!");
						}else if(args[0].equalsIgnoreCase("add")){
							p.setLevel(p.getLevel() + new Integer(number));
							p.sendMessage(EssentialsGreen.prefix + "The xp set succesfully!");
						}else p.sendMessage(EssentialsGreen.prefix + "/xp <set|add> <xp> [Player]");
					}else p.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Give a number!");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] You must be a Player!");
			}else if(args.length > 2){
				String number = args[1];
				if(StringAPI.isInteger(number)){
					Player target = Bukkit.getPlayer(args[2]);
					if(!(target == null)){
						if(args[0].equalsIgnoreCase("set")){
							target.setLevel(new Integer(number));
							sender.sendMessage(EssentialsGreen.prefix + "The xp set succesfully!");
						}else if(args[0].equalsIgnoreCase("add")){
							target.setLevel(target.getLevel() + new Integer(number));
							sender.sendMessage(EssentialsGreen.prefix + "The xp set succesfully!");
						}else sender.sendMessage(EssentialsGreen.prefix + "/xp <set|add> <xp> [Player]");
					}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] The player is not online");
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Give a number!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
	return true;
	}
}