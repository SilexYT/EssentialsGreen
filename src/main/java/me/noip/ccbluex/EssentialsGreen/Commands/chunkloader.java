package me.noip.ccbluex.EssentialsGreen.Commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public class chunkloader implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.chunkloader")){
			if(args.length == 0){
				sender.sendMessage(EssentialsGreen.prefix + " §7◊ §e§lCommands §r§7◊\n"
						+ "§e/chunkloader add [X Y Z World]\n"
						+ "§e/chunkloader remove [X Y Z World]\n"
						+ "§e/chunkloader info");
			}else if(args.length == 1){
				if(sender instanceof Player){
					Player p = (Player)sender;
					if(args[0].equalsIgnoreCase("add")){
						try{
							EssentialsGreen.getEssentialsGreenManager().getChunkLoaderManager().addChunkLoader(p.getLocation());
							p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chunkloaderadd"));
						}catch (IOException e){
							p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chunkloadertheconfigcannotedit"));
							e.printStackTrace();
						}
					}else if(args[0].equalsIgnoreCase("remove")){
						try{
							EssentialsGreen.getEssentialsGreenManager().getChunkLoaderManager().removeChunkLoader(p.getLocation());
							p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chunkloaderremove"));
						}catch (IOException e){
							p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("chunkloadertheconfigcannotedit"));
							e.printStackTrace();
						}
					}else if(args[0].equalsIgnoreCase("info")){
						EssentialsGreen.getEssentialsGreenManager().getChunkLoaderManager().info(p.getLocation(), p);
					}
				}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youmustplayer"));
			}
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}