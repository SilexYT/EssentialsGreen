package me.noip.ccbluex.EssentialsGreen.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

@SuppressWarnings("deprecation")
public class logger implements Listener {

	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("PlayerJoinLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
				File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), e.getJoinMessage());
				try{
					Yaml.save(file);
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}

	@EventHandler
	public void PlayerLeave(PlayerQuitEvent e){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("PlayerQuitLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
				File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), e.getQuitMessage());
				try{
					Yaml.save(file);
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}

	@EventHandler
	public void AsyncPlayerChatListener(AsyncPlayerChatEvent e){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("ChatLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
			    File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), e.getMessage());
				try{
					Yaml.save(file);
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}

	@EventHandler
	public void PlayerCommandPreprocessListener(PlayerCommandPreprocessEvent e){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("CommandLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
			    File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date) + " | World : " + e.getPlayer().getWorld().getName() + " | Username : " + e.getPlayer().getName(), e.getMessage());
				try{
					Yaml.save(file);
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}

	@EventHandler
	public void PlayerChangedWorldListener(PlayerChangedWorldEvent e){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("ChangeWorldLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
			    File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date) + " | Username : " + e.getPlayer().getName(), "Changed in world: " + e.getPlayer().getWorld().getName());
				try{
					Yaml.save(file);
				}catch(IOException e1){
					
				}
			}
		}
	}
	
	@EventHandler
	public void BlockPlace(BlockPlaceEvent e){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("BlockPlaceLog").equalsIgnoreCase("true")){
				Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
			    File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date) + " | Username : " + e.getPlayer().getName(), ": Block place | Type: " + e.getBlock().getType() + " | can build: " + e.canBuild() + " | data: " + e.getBlock().getData());
				try{
					Yaml.save(file);
				}catch(IOException e1){
					
				}
			}
		}
	}
	
	@EventHandler
	public void BlockPlace(BlockBreakEvent e){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("BlockBreakLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
			    File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date) + " | Username : " + e.getPlayer().getName(), ": Block break | Type: " + e.getBlock().getType() + " | can break: " + !e.isCancelled() + " | data: " + e.getBlock().getData());
				try{
					Yaml.save(file);
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void startserver(){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("BlockBreakLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
			    File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date), "The server is starting...");
				try{
					Yaml.save(file);
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void stopserver(){
		if(EssentialsGreen.maintance.getConfig().getString("Logger").equalsIgnoreCase("true")){
			if(EssentialsGreen.maintance.getConfig().getString("BlockBreakLog").equalsIgnoreCase("true")){
			    Date date = new Date();
			    SimpleDateFormat time = new SimpleDateFormat("dd_MM_yyyy-HH:mm:ss");
			    File file = new File(EssentialsGreen.maintance.getConfig().getString("LoggerFile"));
				YamlConfiguration Yaml = YamlConfiguration.loadConfiguration(file);
				
				Yaml.set(time.format(date), "The server is stopping...");
				try{
					Yaml.save(file);
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}
}