package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.Listeners.InteractListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tree implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(sender.hasPermission("EssentialsGreen.tree")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + "/tree <Oak,Spruce,Birch,Jungle,Acacia,Dark_Oak>");
			}else if(sender instanceof Player){
				Player p = (Player)sender;
				if(args[0].equalsIgnoreCase("Oak")){
					if(p.getItemInHand() == null){
						InteractListener.Types.put(p.getName(), "Tree,Oak,0");
						p.sendMessage(EssentialsGreen.prefix + "You can with the HAND Oak tree generate!");
					}else{
						InteractListener.Types.put(p.getName(), "Tree,Oak," + p.getItemInHand().getTypeId());
						p.sendMessage(EssentialsGreen.prefix + "You can with the " + p.getItemInHand().getType() + " Oak tree generate!");
					}
				}else if(args[0].equalsIgnoreCase("Spruce")){
					if(p.getItemInHand() == null){
						InteractListener.Types.put(p.getName(), "Tree,Spruce,0");
						p.sendMessage(EssentialsGreen.prefix + "You can with the HAND Spruce tree generate!");
					}else{
						InteractListener.Types.put(p.getName(), "Tree,Spruce," + p.getItemInHand().getTypeId());
						p.sendMessage(EssentialsGreen.prefix + "You can with the " + p.getItemInHand().getType() + " Spruce tree generate!");
					}
				}
			}
		}
		return true;
	}
}