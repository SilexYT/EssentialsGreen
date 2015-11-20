package me.noip.ccbluex.EssentialsGreen.Commands;

import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.APIs.StringAPI;
import me.noip.ccbluex.EssentialsGreen.Player.User;

public class ban implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, final String[] args) {
		if(p.hasPermission("EssentialsGreen.ban")){
			if(args.length < 2){
				p.sendMessage(EssentialsGreen.prefix + "/ban <Player/uuid> <Reason...>");
			}else{
				String Reason = StringAPI.toCompleteString(args, 1);
				if(EssentialsGreen.getEssentialsGreenManager().getUserManager().existUser(args[0])){
					User user = EssentialsGreen.getEssentialsGreenManager().getUserManager().getUser(args[0]);
					if(user.getBan().isBanned() == false){
						try{
							user.getBan().setBan(true, Reason, p.getName(), new Date());
						}catch(Exception e){
							e.printStackTrace();
						}
						p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Playerisnowbanned").toString().replace("{banplayer}", args[0]));
					}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Thisplayerhasbeenbanned").toString().replace("{banplayer}", args[0]));
				}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Playerisneverontheserver").toString().replace("{banplayer}", args[0]));
			}
		}else p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}