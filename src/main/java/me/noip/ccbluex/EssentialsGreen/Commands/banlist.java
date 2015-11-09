package me.noip.ccbluex.EssentialsGreen.Commands;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.Player.User;
import me.noip.ccbluex.EssentialsGreen.util.Ban;

public class banlist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.Banlist")){
			sender.sendMessage(EssentialsGreen.prefix + "§7[§4Ban§elist§7]");
			Collection<OfflinePlayer> bannedp = Bukkit.getBannedPlayers();
			for(OfflinePlayer op : bannedp){
				User user = EssentialsGreen.getEssentialsGreenManager().getUserManager().getUser(op.getUniqueId());
				Ban ban = user.getBan();
				sender.sendMessage("§4Player§7:§e " + op.getName() + " §7| §4Reason§7:§e " + ban.getReason() + " §7| §4Author§7:§e " + ban.getAuthor() + " §7| §4Date§7:§e " + ban.getDate());
			}
			sender.sendMessage("§7Bans: §4" + bannedp.size());
		}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
		return true;
	}
}