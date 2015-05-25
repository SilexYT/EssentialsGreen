package gg.web.mcb.EssentialsGreen.ListenerFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("unchecked")
public class MainListener implements Listener {
	
	EssentialsGreen plugin;
	
	public MainListener(EssentialsGreen main) {
		plugin = main;
	}
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		UUID U = p.getUniqueId();
		File UserFile = new File("plugins/EssentialsGreen/UserData/" + U.toString() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		boolean b = false;
		if(UserFile.exists()){
			b = true;
		}
		plugin.SetforAllPlayerGroupPrefix();
		UserFileYaml.set("Username", p.getName());
		UserFileYaml.set("UUID", U.toString());
		UserFileYaml.set("IP", p.getAddress().toString());
		UserFileYaml.addDefault("Ban.Enable", "false");
		UserFileYaml.addDefault("Ban.Reason", "null");
		UserFileYaml.options().copyDefaults(true);
		try{UserFileYaml.save(UserFile);}catch(IOException e1){e1.printStackTrace();}
		
		if(UserFileYaml.getString("Ban.Enable").equalsIgnoreCase("true")){
			p.kickPlayer((plugin.getConfig().getString("Ban-Prefix") + UserFileYaml.getString("Ban.Reason")).replace('&', '§'));
			e.setJoinMessage(plugin.getConfig().getString("Ban-JoinBrodcast").replace("{Player}".replace('&', '§'), p.getName()).replace("{Reason}", UserFileYaml.getString("Ban.Reason")).replace('&', '§'));
		}else{
			if(b == true){e.setJoinMessage(plugin.getConfig().getString("JoinMessage").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getName()).replace('&', '§'));}else e.setJoinMessage(plugin.getConfig().getString("FirstJoinMessage").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getName()).replace('&', '§'));
			if(plugin.getConfig().getString("JoinSpawnTeleport").equalsIgnoreCase("true")){
				p.performCommand("Spawn");
			}
		}
	}
	
	@EventHandler
	public void PlayerLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();
		UUID U = p.getUniqueId();
		File UserFile = new File("plugins/EssentialsGreen/UserData/" + U.toString() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		
		if(UserFileYaml.getString("Ban.Enable").equalsIgnoreCase("true")){
			p.kickPlayer(UserFileYaml.getString("Ban.Reason"));
			e.setQuitMessage("");
		}else{
			e.setQuitMessage(plugin.getConfig().getString("LeaveMessage").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getName()).replace('&', '§'));
		}
	}
	
	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		File UserFile = new File("plugins/EssentialsGreen/UserData/" + p.getUniqueId().toString() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		if(p.isOp()){
			e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", "§" + plugin.getConfig().getInt("ops-name-color") + p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
		}else{
			e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", UserFileYaml.getString("GroupPrefix")).replace("{Player}", p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
		}
	}

	
	@EventHandler
	public void CommandListener(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		ArrayList<String> Commands = (ArrayList<String>)plugin.getConfig().get("Commands");
			ArrayList<String> BypassPlayer = (ArrayList<String>)plugin.getConfig().get("CommandBlackListBypassPlayerList");
		if(!p.hasPermission("EssentilasGreen.CommandBlacklist.bypass") | !BypassPlayer.contains(p.getName())){
			for(int i = 0; i < Commands.size(); i++){
				String[] Command = e.getMessage().split(" ");
				if(Command[0].equalsIgnoreCase(Commands.get(i))){
					e.setCancelled(true);
					p.sendMessage(plugin.getConfig().getString("CommandBlockMessage").replace('&', '§'));
				}
			}
		}
	}
}