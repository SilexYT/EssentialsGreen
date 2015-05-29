package gg.web.mcb.EssentialsGreen.ListenerFiles;

import gg.web.mcb.EssentialsGreen.ApiFiles.FarbcodeAPI;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogListener implements Listener {

	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e){
	    Date date = new Date();
	    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
		File file = new File("plugins/EssentialsGreen/LogFile.log");
		YamlConfiguration ChatYaml = YamlConfiguration.loadConfiguration(file);
		
		ChatYaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), FarbcodeAPI.RemoveFarbcodes(e.getJoinMessage()));
		try{ChatYaml.save(file);}catch(IOException e1){}
	}

	@EventHandler
	public void PlayerLeave(PlayerQuitEvent e){
	    Date date = new Date();
	    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
		File file = new File("plugins/EssentialsGreen/LogFile.log");
		YamlConfiguration ChatYaml = YamlConfiguration.loadConfiguration(file);
		
		ChatYaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), FarbcodeAPI.RemoveFarbcodes(e.getQuitMessage()));
		try{ChatYaml.save(file);}catch(IOException e1){}
	}

	@EventHandler
	public void AsyncPlayerChatListener(AsyncPlayerChatEvent e){
	    Date date = new Date();
	    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
		File file = new File("plugins/EssentialsGreen/LogFile.log");
		YamlConfiguration ChatYaml = YamlConfiguration.loadConfiguration(file);
		
		ChatYaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), FarbcodeAPI.RemoveFarbcodes(e.getMessage()));
		try{ChatYaml.save(file);}catch(IOException e1){}
	}

	@EventHandler
	public void PlayerCommandPreprocessListener(PlayerCommandPreprocessEvent e){
	    Date date = new Date();
	    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
		File file = new File("plugins/EssentialsGreen/LogFile.log");
		YamlConfiguration ChatYaml = YamlConfiguration.loadConfiguration(file);
		
		ChatYaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), FarbcodeAPI.RemoveFarbcodes(e.getMessage()));
		try{ChatYaml.save(file);}catch(IOException e1){}
	}

	@EventHandler
	public void PlayerChangedWorldListener(PlayerChangedWorldEvent e){
	    Date date = new Date();
	    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
		File file = new File("plugins/EssentialsGreen/LogFile.log");
		YamlConfiguration ChatYaml = YamlConfiguration.loadConfiguration(file);
		
		ChatYaml.set(time.format(date) + " | Username : " + e.getPlayer().getName(), "Changed in " + e.getPlayer().getWorld().getName());
		try{ChatYaml.save(file);}catch(IOException e1){}
	}
}