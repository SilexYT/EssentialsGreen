package gg.web.mcb.EssentialsGreen.ListenerFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("unchecked")
public class MainListener implements Listener {
	
	EssentialsGreen plugin;
	
	public MainListener(EssentialsGreen main) {
		plugin = main;
	}
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		File UserFile = new File("plugins/EssentialsGreen/UserData/" + p.getName() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		UserFileYaml.set("Username", p.getName());
		UserFileYaml.set("UUID", "[No UUID]");
		UserFileYaml.addDefault("Ban.Enable", "false");
		UserFileYaml.addDefault("Ban.Reason", "null");
		UserFileYaml.options().copyDefaults(true);
		try{UserFileYaml.save(UserFile);}catch(IOException e1){e1.printStackTrace();}
		
		if(UserFileYaml.getString("Ban.Enable").equalsIgnoreCase("true")){
			p.kickPlayer(plugin.getConfig().getString("Ban-Prefix").replace('&', '§') + UserFileYaml.getString("Ban.Reason"));
			e.setJoinMessage(plugin.getConfig().getString("Ban-JoinBrodcast").replace("{Player}".replace('&', '§'), p.getName()).replace("{Reason}", UserFileYaml.getString("Ban.Reason")));
		}else{
			if(plugin.getConfig().getString("JoinSpawnTeleport").equalsIgnoreCase("true")){
				p.performCommand("Spawn");
			}
			e.setJoinMessage(plugin.getConfig().getString("JoinMessage").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", p.getName()).replace('&', '§'));
		}
	}
	
	@EventHandler
	public void PlayerLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();
		File UserFile = new File("plugins/EssentialsGreen/UserData/" + p.getName() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		
		if(UserFileYaml.getString("Ban.Enable").equalsIgnoreCase("true")){
			p.kickPlayer(UserFileYaml.getString("Ban.Reason"));
			e.setQuitMessage("");
		}else{
			e.setQuitMessage(plugin.getConfig().getString("LeaveMessage").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", p.getName()).replace('&', '§'));
		}
	}
	
	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(p.isOp()){
			e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", "§" + plugin.getConfig().getInt("ops-name-color") + p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
		}else{
			e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '§'));
		}
	}

	
	@EventHandler
	public void CommandListener(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		if(!p.hasPermission("EssentilasGreen.CommandBlacklist.bypass")){
			ArrayList<String> commands = (ArrayList<String>)plugin.getConfig().get("Commands");
			String[] command = e.getMessage().split(" ");
			for(int i = 0; i < commands.size(); i++){
				if(command[0].equalsIgnoreCase(commands.get(i))){
					e.setCancelled(true);
				}
			}
		}
	}
}