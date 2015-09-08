package me.noip.ccbluex.EssentialsGreen.Listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public class MainListener implements Listener {
	
	EssentialsGreen plugin;
	public MainListener(EssentialsGreen main) {
		plugin = main;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerLogin(PlayerLoginEvent e){
		File UserFile = new File("plugins/EssentialsGreen/userdata/" + e.getPlayer().getUniqueId().toString() + ".data");
		if(UserFile.exists()){
			YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
			if(UserFileYaml.getString("Ban.Enable").equalsIgnoreCase("true")){
				String Type = UserFileYaml.getString("Ban.Type");
				if(Type.equalsIgnoreCase("Ban")){
					e.disallow(PlayerLoginEvent.Result.KICK_OTHER, (plugin.getConfig().getString("Ban-Message") + "\n§fAuthor: §e" + UserFileYaml.getString("Ban.Author") + " §fDate: §e" + UserFileYaml.getString("Ban.date") + "\n§fExpires in §e" + UserFileYaml.getString("Ban.Ex") + " §fSeconds" + "\n§fReason: §7" + UserFileYaml.getString("Ban.Reason")).replace('&', '§'));
				}else{
					//This is the Time of day
					long time = (System.currentTimeMillis()/1000L) - UserFileYaml.getLong("Ban.dateSecond");
					if(time < UserFileYaml.getInt("Ban.Ex")){
						e.disallow(PlayerLoginEvent.Result.KICK_OTHER, (plugin.getConfig().getString("Ban-Message") + "\n§fAuthor: §e" + UserFileYaml.getString("Ban.Author") + " §fDate: §e" + UserFileYaml.getString("Ban.date") + "\n§fExpires in §e" + time + " §fSeconds" + "\n§fReason: §7" + UserFileYaml.getString("Ban.Reason")).replace('&', '§'));
					}else{
						e.allow();
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unban " + e.getPlayer().getName());
					}
				}
			}
		}
	}

	@EventHandler(priority= EventPriority.HIGH)
	public void PlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		File UserFile = new File("plugins/EssentialsGreen/userdata/" + p.getUniqueId().toString() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		//setPlayerInfos
		UserFileYaml.set("Username", p.getName());
		UserFileYaml.set("IP", p.getAddress().toString());
		UserFileYaml.addDefault("IsNewPlayer", true);
		UserFileYaml.addDefault("Ban.Enable", "false");
		UserFileYaml.addDefault("Ban.Reason", "null");
		UserFileYaml.addDefault("Ban.Author", "null");
		UserFileYaml.addDefault("Ban.date", "null");
		UserFileYaml.addDefault("Ban.Ex", "null");
		UserFileYaml.addDefault("Ban.dateSeconds", "null");
		//GroupPrefix
		try{
			Class.forName("ru.tehkode.permissions.bukkit.PermissionsEx");
		    UserFileYaml.set("GroupPrefix", ru.tehkode.permissions.bukkit.PermissionsEx.getUser(p).getPrefix());
		}catch(ClassNotFoundException e2){
			UserFileYaml.set("GroupPrefix", "");
		}
		//JoinMessage
		if(!UserFileYaml.getBoolean("IsNewPlayer")){
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
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(new File("plugins/EssentialsGreen/userdata/" + p.getUniqueId().toString() + ".data"));
		e.setQuitMessage(plugin.getConfig().getString("LeaveMessage").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getDisplayName()).replace('&', '§'));
	}

	@EventHandler(priority= EventPriority.HIGHEST)
	public void PlayerChatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		File UserFile = new File("plugins/EssentialsGreen/userdata/" + p.getUniqueId().toString() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		if(p.isOp()){
			e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", "§" + plugin.getConfig().getInt("ops-name-color") + p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
		}else e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
	}

	@EventHandler
	public void PlayerDeth(PlayerDeathEvent e){
		Player p = e.getEntity();
		e.setDeathMessage(e.getDeathMessage().replace(p.getName(), p.getDisplayName()));
	}
}