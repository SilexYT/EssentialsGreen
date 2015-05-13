package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission("EssentialsGreen.gamemode")){
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/gamemode <0|1|2|3> or /gamemode <0|1|2|3> <Target>");
				}else if(args.length == 1){
					if(args[0].equalsIgnoreCase("0")){
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else if(args[0].equalsIgnoreCase("1")){
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else if(args[0].equalsIgnoreCase("2")){
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else if(args[0].equalsIgnoreCase("3")){
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else if(args[0].equalsIgnoreCase("survival")){
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else if(args[0].equalsIgnoreCase("creative")){
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else if(args[0].equalsIgnoreCase("adventure")){
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else if(args[0].equalsIgnoreCase("spectator")){
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed");
					}else p.sendMessage(EssentialsGreen.prefix + "/gamemode <0|1|2|3> or /gamemode <0|1|2|3> <Target>");
				}else if(args.length == 2){
					Player Target = Bukkit.getPlayer(args[1]);
					if(!(Target == null)){
						if(args[0].equalsIgnoreCase("0")){
							Target.setGameMode(GameMode.SURVIVAL);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else if(args[0].equalsIgnoreCase("1")){
							Target.setGameMode(GameMode.CREATIVE);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else if(args[0].equalsIgnoreCase("2")){
							Target.setGameMode(GameMode.ADVENTURE);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else if(args[0].equalsIgnoreCase("3")){
							Target.setGameMode(GameMode.SPECTATOR);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else if(args[0].equalsIgnoreCase("survival")){
							Target.setGameMode(GameMode.SURVIVAL);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else if(args[0].equalsIgnoreCase("creative")){
							Target.setGameMode(GameMode.CREATIVE);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else if(args[0].equalsIgnoreCase("adventure")){
							Target.setGameMode(GameMode.ADVENTURE);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else if(args[0].equalsIgnoreCase("spectator")){
							Target.setGameMode(GameMode.SPECTATOR);
							Target.sendMessage(EssentialsGreen.prefix + "Your gamemode has been changed by " + p.getName());
							p.sendMessage(EssentialsGreen.prefix + Target.getName() + " gamemode has been changed");
						}else p.sendMessage(EssentialsGreen.prefix + "/gamemode <0|1|2|3> or /gamemode <0|1|2|3> <Target>");
					}else p.sendMessage(EssentialsGreen.prefix + "This target player is not online");
				}
			}else p.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		}else System.out.println("[EssentialsGreen] You must be a Player!");
		return true;
	}
}