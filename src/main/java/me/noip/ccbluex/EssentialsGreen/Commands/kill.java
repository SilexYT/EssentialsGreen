package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class kill implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.kill")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/kill <Player|@a|@e [World]>");
			}else if(args.length > 0){
				if(args[0].equalsIgnoreCase("@a")){
					for(Player p : Bukkit.getOnlinePlayers()){
						p.setHealth(0.0);
					}
					sender.sendMessage(EssentialsGreen.prefix + "All Players killed!");
				}else if(args[0].equalsIgnoreCase("@e")){
					if(args.length > 1){
						World W = Bukkit.getWorld(args[1]); 
						if(!(W == null)){
							for(Player p : Bukkit.getOnlinePlayers()){
								if(p.getWorld().getName().equalsIgnoreCase(args[1])){
									p.setHealth(0.0);
								}
							}
							List<Entity> e = Bukkit.getWorld(args[1]).getEntities();
							for(int i = 0; e.size() > i; i++){
								Entity E = e.get(i);
								if(!E.getType().equals(EntityType.PLAYER)){
									E.remove();
								}
							}
							sender.sendMessage(EssentialsGreen.prefix + "All Entitys killed!");
						}else sender.sendMessage(EssentialsGreen.prefix + "�4[�lError�r�4] The World exist not!");
					}else{
						if(sender instanceof Player){
							Player p = (Player)sender;
							for(Player pp : Bukkit.getOnlinePlayers()){
								if(pp.getWorld().getName().equalsIgnoreCase(p.getWorld().getName())){
									pp.setHealth(0.0);
								}
							}
							List<Entity> e = p.getWorld().getEntities();
							for(int i = 0; e.size() > i; i++){
								Entity E = e.get(i);
								if(!E.getType().equals(EntityType.PLAYER)){
									E.remove();
								}
							}
							sender.sendMessage(EssentialsGreen.prefix + "All Entitys killed!");
						}else sender.sendMessage(EssentialsGreen.prefix + "�4[�lError�r�4] provide a World");
					}
				}else{
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)){
						target.setHealth(0.0);
						sender.sendMessage(EssentialsGreen.prefix + "Player killed!");
					}else sender.sendMessage(EssentialsGreen.prefix + "�4[�lError�r�4] The player is not online");
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}