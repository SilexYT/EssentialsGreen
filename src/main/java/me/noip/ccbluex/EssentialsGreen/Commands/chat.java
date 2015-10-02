package me.noip.ccbluex.EssentialsGreen.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public class chat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.chat")){
			if(args.length == 0 | args.length > 2){
				sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chatmanagerprefix").toString() + "\n"
						+ "§e/chat clear [Player|@a]\n"
						+ "§e/chat send <Message>\n"
						+ "§e/chat lock");
			}else if(args.length > 0){
				if(args[0].equalsIgnoreCase("clear")){
					if(args.length > 2){
						
					}
				}
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions").toString());
		return true;
	}
}