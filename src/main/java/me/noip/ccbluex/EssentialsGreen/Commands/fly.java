package me.noip.ccbluex.EssentialsGreen.Commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public class fly implements CommandExecutor {
	ArrayList<String> FlyingPlayers = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.fly")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(!p.getGameMode().equals(GameMode.CREATIVE)){
						String PN = p.getName();
						if(FlyingPlayers.contains(PN)){
							p.setAllowFlight(false);
							p.setFlying(false);
							p.sendMessage(EssentialsGreen.prefix + "Fly Mode changed!");
							FlyingPlayers.remove(PN);
						}else{
							p.setAllowFlight(true);
							p.setFlying(true);
							p.sendMessage(EssentialsGreen.prefix + "Fly Mode changed!");
							FlyingPlayers.add(PN);
						}
					}else p.sendMessage(EssentialsGreen.prefix + "�4[�lError�r�4] This event is only for people without Creative Mode!");
				}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youmustplayer"));
			}else{
				if(args.length > 0){
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						if(!target.getGameMode().equals(GameMode.CREATIVE)){
							if(FlyingPlayers.contains(args[0])){
								target.setAllowFlight(false);
								target.setFlying(false);
								target.sendMessage(EssentialsGreen.prefix + "Fly Mode changed!");
								sender.sendMessage(EssentialsGreen.prefix + "Fly Mode changed from " + args[0] + "!");
								FlyingPlayers.remove(args[0]);
							}else{
								target.setAllowFlight(true);
								target.setFlying(true);
								target.sendMessage(EssentialsGreen.prefix + "Fly Mode changed!");
								sender.sendMessage(EssentialsGreen.prefix + "Fly Mode changed from " + args[0] + "!");
								FlyingPlayers.add(args[0]);
							}
						}else sender.sendMessage(EssentialsGreen.prefix + "�4[�lError�r�4] This player is not online");
					}else sender.sendMessage(EssentialsGreen.prefix + "�4[�lError�r�4] This event is only for people without Creative Mode!");
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}