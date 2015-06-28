package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@SuppressWarnings({ "deprecation", "unused" })
public class spawnmob implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.spawnmob")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + "/spawnmob <Mob> [Ammount]\n"
							+ "Mob\n"
							+ "Bat,Blaze,Cave_Spider,Chicken,Cow\n"
							+ "Creeper,Ender_Crystal,Ender_Dragon,EnderMan\n"
							+ "EnderMite,Ghast,Giant,Guardian\n"
							+ "Horse,Iron_Golem,Rabbit,Sheep\n"
							+ "Silverfish,Skeleton,Slime,Snowman\n"
							+ "Spider,Squid,Villager,Witch\n"
							+ "Wither,Wolf,Zombie");
					
				}else if(args.length > 0){
					if(args.length == 1){
						EntityType E = EntityType.fromName(args[0]);
						if(E != null){
							p.getWorld().spawnEntity(p.getLocation(), E);
						}else p.sendMessage(EssentialsGreen.prefix + "This mob exist not!");
					}else{
						if(new Integer(args[1]) != null){
							EntityType E = EntityType.fromName(args[0]);
							if(E != null){
								for(int i = 0; new Integer(args[1]) > i; i++){
									p.getWorld().spawnEntity(p.getLocation(), E);
								}
							}else p.sendMessage(EssentialsGreen.prefix + "This mob exist not!");
						}else p.getWorld().spawnEntity(p.getLocation(), EntityType.fromName(args[0]));
					}
				}
			}else sender.sendMessage(EssentialsGreen.prefix + "You must be a Player");
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}