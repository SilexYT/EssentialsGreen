package me.noip.ccbluex.EssentialsGreen.Commands;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.Player.User;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class unban implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args) {
		if(p.hasPermission("EssentialsGreen.unban")){
			if(args.length == 0){
				p.sendMessage(EssentialsGreen.prefix + "/unban <Player>");
			}else if(args.length > 0){
				if(EssentialsGreen.getEssentialsGreenManager().getUserManager().existUser(args[0])){
					User user = EssentialsGreen.getEssentialsGreenManager().getUserManager().getUser(args[0]);
					if(user.getBan().isBanned() == true){
						try{
							user.getBan().setBan(false, null, null, null);
						}catch (Exception e){
							e.printStackTrace();
						}
						p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Playerisnotmorebanned").toString().replace("{banplayer}", args[0]));
					}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Playerisnotbanned").toString().replace("{banplayer}", args[0]));
				}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Playerisneverontheserver").toString().replace("{banplayer}", args[0]));
			}else p.sendMessage(EssentialsGreen.prefix + "/unban <Player>");
		}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}