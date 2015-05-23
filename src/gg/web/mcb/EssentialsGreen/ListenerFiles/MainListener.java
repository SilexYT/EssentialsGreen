package gg.web.mcb.EssentialsGreen.ListenerFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
		UUID U = p.getUniqueId();
		File UserFile = new File("plugins/EssentialsGreen/UserData/" + U.toString() + ".data");
		YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
		boolean b = false;
		if(UserFile.exists()){
			b = true;
		}
		UserFileYaml.set("Info.Username", p.getName());
		UserFileYaml.set("Info.UUID", U.toString());
		UserFileYaml.set("Info.IP", p.getAddress().toString());
		UserFileYaml.addDefault("Ban.Enable", "false");
		UserFileYaml.addDefault("Ban.Reason", "null");
		UserFileYaml.options().copyDefaults(true);
		try{UserFileYaml.save(UserFile);}catch(IOException e1){e1.printStackTrace();}
		
		if(UserFileYaml.getString("Ban.Enable").equalsIgnoreCase("true")){
			p.kickPlayer((plugin.getConfig().getString("Ban-Prefix") + UserFileYaml.getString("Ban.Reason")).replace('&', '�'));
			e.setJoinMessage(plugin.getConfig().getString("Ban-JoinBrodcast").replace("{Player}".replace('&', '�'), p.getName()).replace("{Reason}", UserFileYaml.getString("Ban.Reason")).replace('&', '�'));
		}else{
			EssentialsGreen.OnlinePlayers.add(p.getName());
			if(plugin.getConfig().getString("JoinSpawnTeleport").equalsIgnoreCase("true")){
				p.performCommand("Spawn");
			}
			if(b == true){
				e.setJoinMessage(plugin.getConfig().getString("JoinMessage").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", p.getName()).replace('&', '�'));
			}else{
				e.setJoinMessage(plugin.getConfig().getString("FirstJoinMessage").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", p.getName()).replace('&', '�'));
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
			e.setQuitMessage(plugin.getConfig().getString("LeaveMessage").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", p.getName()).replace('&', '�'));
			EssentialsGreen.OnlinePlayers.remove(p.getName());
		}
	}
	
	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(p.isOp()){
			e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", "�" + plugin.getConfig().getInt("ops-name-color") + p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '�'));
		}else{
			e.setFormat(plugin.getConfig().getString("ChatFormat").replace("{Group}", PermissionsEx.getUser(p).getPrefix()).replace("{Player}", p.getDisplayName()).replace("{Message}", e.getMessage()).replace('&', '�'));
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
					p.sendMessage(plugin.getConfig().getString("CommandBlockMessage").replace('&', '�'));
				}
			}
		}
	}
	
	@EventHandler
	public void BlockPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(e.getBlock().getType() == Material.COMMAND){
			if(!p.isOp()){
				p.sendMessage(EssentialsGreen.prefix + "You must be a OP!");
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void BlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(e.getBlock().getType() == Material.COMMAND){
			if(!p.isOp()){
				p.sendMessage(EssentialsGreen.prefix + "You must be a OP!");
				e.setCancelled(true);
			}
		}
	}
}