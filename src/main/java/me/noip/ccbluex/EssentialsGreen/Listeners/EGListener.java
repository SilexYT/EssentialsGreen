package me.noip.ccbluex.EssentialsGreen.Listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.Player.User;

public class EGListener implements Listener {
	
	EssentialsGreen plugin;
	public EGListener(EssentialsGreen main) {
		plugin = main;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerLogin(PlayerLoginEvent e){
		Player p = e.getPlayer();
		User user = EssentialsGreen.getEssentialsGreenManager().getUserManager().getUser(p.getUniqueId());
		if(e.getResult() == Result.KICK_BANNED){
			try{
				e.setKickMessage(ChatColor.translateAlternateColorCodes('&', EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Ban-Message") + "\n§fAuthor: §e" + user.getBan().getAuthor() + " §fDate: §e" + user.getBan().getDate() + "\n§fReason: §7" + user.getBan().getReason()));
			}catch(NullPointerException npe){
				e.allow();
				Bukkit.broadcastMessage(EssentialsGreen.prefix + "PROBLEM: User file can not read! The user is allow to join | User: " + p.getName());
			}
		}
	}

	@EventHandler(priority= EventPriority.HIGH)
	public void PlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		//Create or Update the user file from the player!
		try{
			if(EssentialsGreen.getEssentialsGreenManager().getUserManager().existUser(p.getUniqueId()) == false){
				EssentialsGreen.getEssentialsGreenManager().getUserManager().createUser(p, true);
				EssentialsGreen.getEssentialsGreenManager().ReloadGroupPrefix();
			}else{
				EssentialsGreen.getEssentialsGreenManager().getUserManager().getUser(p.getName()).UpdateUserFile();
				EssentialsGreen.getEssentialsGreenManager().ReloadGroupPrefix();
			}
		}catch(Exception exep){
			exep.printStackTrace();
		}
		//Load user file...
		File UserFile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		//JoinMessage
		if(UserFileYaml.getBoolean("IsNewPlayer") == false){
			e.setJoinMessage((plugin.getConfig().getString("JoinMessage").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getDisplayName())).replace('&', '§'));
		}else{
			e.setJoinMessage((plugin.getConfig().getString("FirstJoinMessage").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getDisplayName())).replace('&', '§'));
			UserFileYaml.set("IsNewPlayer", false);
			try{
				UserFileYaml.options().copyDefaults(true);
				UserFileYaml.save(UserFile);
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
		//SpawnTeleport with a if methode
		if(plugin.getConfig().getString("JoinSpawnTeleport").equalsIgnoreCase("true")){
			p.performCommand("Spawn");
		}
	}

	@EventHandler
	public void PlayerLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user"));
		e.setQuitMessage(plugin.getConfig().getString("LeaveMessage").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getDisplayName()).replace('&', '§'));
	}

	@EventHandler(priority= EventPriority.HIGHEST)
	public void PlayerChatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		User user = EssentialsGreen.getEssentialsGreenManager().getUserManager().getUser(p.getName());
		File UserFile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		//Check: is player mute?
		if(user.isMute() == false){
			//Check: is player op?
			if(p.isOp()){
				e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", "§" + plugin.getConfig().getInt("ops-name-color") + p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
			}else{
				e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
			}
		}else{
			//Cancel the event!
			e.setCancelled(true);
			//Mute message
			p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youmute").toString());
		}
	}

	@EventHandler
	public void PlayerDeth(PlayerDeathEvent e){
		Player p = e.getEntity();
		e.setDeathMessage(e.getDeathMessage().replace(p.getName(), p.getDisplayName()));
	}
}