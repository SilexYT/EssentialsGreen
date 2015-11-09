/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

/**
 * @author Marco
 *
 */
public class servermanage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender.hasPermission("EssentialsGreen.servermanage")){
			if(label.equalsIgnoreCase("reload") | label.equalsIgnoreCase("rl")){
				Bukkit.broadcastMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("ReloadStartMessage").toString().replace("{Player}", sender.getName()));
				Bukkit.reload();
				Bukkit.reloadWhitelist();
				Bukkit.broadcastMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("ReloadFinishMessage").toString());
			}else if(label.equalsIgnoreCase("stop")){
				Bukkit.broadcastMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("StopMessage").toString().replace("{Player}", sender.getName()));
				for(Player p : Bukkit.getOnlinePlayers()){
					p.kickPlayer(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("StopMessage").toString().replace("{Player}", sender.getName()));
				}
				Bukkit.shutdown();
			}else{
				sender.sendMessage(EssentialsGreen.prefix + " §7Commands:\n"
						+ "/rl"
						+ "/stop");
			}
		}
		return true;
	}
}