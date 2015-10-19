package me.noip.ccbluex.EssentialsGreen;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;
import org.xml.sax.SAXException;

import me.noip.ccbluex.EssentialsGreen.CommandManager.onTabCompleteManager;
import me.noip.ccbluex.EssentialsGreen.Commands.asConsole;
import me.noip.ccbluex.EssentialsGreen.Commands.ban;
import me.noip.ccbluex.EssentialsGreen.Commands.banlist;
import me.noip.ccbluex.EssentialsGreen.Commands.broadcast;
import me.noip.ccbluex.EssentialsGreen.Commands.chat;
//import me.noip.ccbluex.EssentialsGreen.Commands.chunkloader;
import me.noip.ccbluex.EssentialsGreen.Commands.clear;
import me.noip.ccbluex.EssentialsGreen.Commands.defaultgamemode;
import me.noip.ccbluex.EssentialsGreen.Commands.effect;
import me.noip.ccbluex.EssentialsGreen.Commands.fly;
import me.noip.ccbluex.EssentialsGreen.Commands.gamemode;
import me.noip.ccbluex.EssentialsGreen.Commands.give;
import me.noip.ccbluex.EssentialsGreen.Commands.heal;
import me.noip.ccbluex.EssentialsGreen.Commands.invsee;
import me.noip.ccbluex.EssentialsGreen.Commands.kick;
import me.noip.ccbluex.EssentialsGreen.Commands.kill;
import me.noip.ccbluex.EssentialsGreen.Commands.list;
import me.noip.ccbluex.EssentialsGreen.Commands.msg;
import me.noip.ccbluex.EssentialsGreen.Commands.nick;
import me.noip.ccbluex.EssentialsGreen.Commands.reload;
import me.noip.ccbluex.EssentialsGreen.Commands.say;
import me.noip.ccbluex.EssentialsGreen.Commands.seed;
import me.noip.ccbluex.EssentialsGreen.Commands.setspawn;
import me.noip.ccbluex.EssentialsGreen.Commands.setworldspawn;
import me.noip.ccbluex.EssentialsGreen.Commands.skull;
import me.noip.ccbluex.EssentialsGreen.Commands.spawn;
import me.noip.ccbluex.EssentialsGreen.Commands.spawnmob;
import me.noip.ccbluex.EssentialsGreen.Commands.spawnpoint;
import me.noip.ccbluex.EssentialsGreen.Commands.speed;
import me.noip.ccbluex.EssentialsGreen.Commands.stop;
import me.noip.ccbluex.EssentialsGreen.Commands.tempban;
import me.noip.ccbluex.EssentialsGreen.Commands.time;
import me.noip.ccbluex.EssentialsGreen.Commands.tp;
import me.noip.ccbluex.EssentialsGreen.Commands.tpall;
import me.noip.ccbluex.EssentialsGreen.Commands.unban;
import me.noip.ccbluex.EssentialsGreen.Commands.vanish;
import me.noip.ccbluex.EssentialsGreen.Commands.warp;
import me.noip.ccbluex.EssentialsGreen.Commands.whitelist;
import me.noip.ccbluex.EssentialsGreen.Commands.xp;
import me.noip.ccbluex.EssentialsGreen.Listeners.ChunkLoaderListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.logger;
import me.noip.ccbluex.EssentialsGreen.Listeners.EGListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.PhysicExplosionListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.Signs;
import me.noip.ccbluex.EssentialsGreen.managers.EssentialsGreenManager;

public class EssentialsGreen extends JavaPlugin {

	public static String prefix;
	public File SpawnF;
	public YamlConfiguration SpawnYaml;
	Metrics metrics;
	FileConfiguration conf;

	@Override
	public void onLoad() {
		//File
		File egfile = new File("plugins/EssentialsGreen");
		egfile.mkdir();
		//Userdate File
		File UserdataFile = new File("plugins/EssentialsGreen/users");
		UserdataFile.mkdir();
		//Warp File
		File WarpFile = new File("plugins/EssentialsGreen/Warp");
		WarpFile.mkdir();
		//Message File
		try{
			File messagefile = new File("plugins/EssentialsGreen/message.yml");
			if(messagefile.exists() == false){
				messagefile.createNewFile();
				getEssentialsGreenManager().getMessageManager().load();
			}
			prefix = getEssentialsGreenManager().getMessageManager().getMessage("prefix") + " ";
		}catch(IOException e){
			e.printStackTrace();
		}
		//Message file extra load;
		YamlConfiguration messageyaml = getEssentialsGreenManager().getFileManager().getYaml("plugins/EssentialsGreen/message.yml");
		//Config File
		saveDefaultConfig();
		if(Double.parseDouble(getEssentialsGreenManager().getVersion()) > getConfig().getDouble("ConfigVersion")){
			File oldc = getEssentialsGreenManager().getFileManager().getFile("plugins/EssentialsGreen/config.yml");
			try {
				getEssentialsGreenManager().getFileManager().copyfile(oldc, new File("plugins/EssentialsGreen/oldconfig.yml"));
			}catch(IOException e){
				e.printStackTrace();
			}
			oldc.delete();
			saveDefaultConfig();
			System.err.println(prefix + messageyaml.getString("renewconfig").replace('&', '§'));
		}
		//Spawn File
		SpawnF = new File("plugins/EssentialsGreen/Spawn.yml");
		SpawnYaml = YamlConfiguration.loadConfiguration(SpawnF);
		try{
			SpawnYaml.save(SpawnF);
		}catch (IOException e){
			e.printStackTrace();
		}
		//AUTOUPDATER!  
		try{
			getEssentialsGreenManager().getUpdateManager().REGISTERXML();
			if(getEssentialsGreenManager().getUpdateManager().updateNeeded()){
				System.out.println(prefix + messageyaml.getString("NewVersion").replace('&', '§'));
				if(getConfig().getString("AutoUpdate").equalsIgnoreCase("true")){
					//Is working!
				}
			}else System.out.println(prefix + messageyaml.getString("NoNewVersion").replace('&', '§'));
		}catch(IOException | SAXException | ParserConfigurationException e1){
			e1.printStackTrace();
		}
		//ChunkLoader start
		try{
			getEssentialsGreenManager().getChunkLoaderManager().start();
		}catch (IOException e){
			e.printStackTrace();
		}
		System.out.println(prefix + messageyaml.getString("isload").replace('&', '§'));
	}
	
	@Override
	public void onEnable(){
		getCommand("tp").setExecutor(new tp());
		getCommand("tp").setTabCompleter(new onTabCompleteManager(this));
		getCommand("gamemode").setExecutor(new gamemode());
		getCommand("gamemode").setTabCompleter(new onTabCompleteManager(this));
		getCommand("setspawn").setExecutor(new setspawn(this));
		getCommand("setspawn").setTabCompleter(new onTabCompleteManager(this));
		getCommand("spawn").setExecutor(new spawn(this));
		getCommand("spawn").setTabCompleter(new onTabCompleteManager(this));
		getCommand("kick").setExecutor(new kick(this));
		getCommand("kick").setTabCompleter(new onTabCompleteManager(this));
		getCommand("ban").setExecutor(new ban(this));
		getCommand("ban").setTabCompleter(new onTabCompleteManager(this));
		getCommand("unban").setExecutor(new unban(this));
		getCommand("unban").setTabCompleter(new onTabCompleteManager(this));
		getCommand("banlist").setExecutor(new banlist());
		getCommand("banlist").setTabCompleter(new onTabCompleteManager(this));
		getCommand("give").setExecutor(new give());
		getCommand("give").setTabCompleter(new onTabCompleteManager(this));
		getCommand("msg").setExecutor(new msg());
		getCommand("msg").setTabCompleter(new onTabCompleteManager(this));
		getCommand("time").setExecutor(new time());
		getCommand("time").setTabCompleter(new onTabCompleteManager(this));
		getCommand("xp").setExecutor(new xp());
		getCommand("xp").setTabCompleter(new onTabCompleteManager(this));
		getCommand("broadcast").setExecutor(new broadcast());
		getCommand("broadcast").setTabCompleter(new onTabCompleteManager(this));
		getCommand("say").setExecutor(new say());
		getCommand("say").setTabCompleter(new onTabCompleteManager(this));
		getCommand("kill").setExecutor(new kill());
		getCommand("kill").setTabCompleter(new onTabCompleteManager(this));
		getCommand("fly").setExecutor(new fly());
		getCommand("fly").setTabCompleter(new onTabCompleteManager(this));
		getCommand("speed").setExecutor(new speed());
		getCommand("speed").setTabCompleter(new onTabCompleteManager(this));
		getCommand("invsee").setExecutor(new invsee());
		getCommand("invsee").setTabCompleter(new onTabCompleteManager(this));
		getCommand("heal").setExecutor(new heal());
		getCommand("heal").setTabCompleter(new onTabCompleteManager(this));
		getCommand("nick").setExecutor(new nick(this));
		getCommand("nick").setTabCompleter(new onTabCompleteManager(this));
		getCommand("setworldspawn").setExecutor(new setworldspawn());
		getCommand("setworldspawn").setTabCompleter(new onTabCompleteManager(this));
		getCommand("seed").setExecutor(new seed());
		getCommand("seed").setTabCompleter(new onTabCompleteManager(this));
		getCommand("clear").setExecutor(new clear());
		getCommand("clear").setTabCompleter(new onTabCompleteManager(this));
		getCommand("list").setExecutor(new list());
		getCommand("list").setTabCompleter(new onTabCompleteManager(this));
		getCommand("defaultgamemode").setExecutor(new defaultgamemode());
		getCommand("defaultgamemode").setTabCompleter(new onTabCompleteManager(this));
		getCommand("stop").setExecutor(new stop());
		getCommand("stop").setTabCompleter(new onTabCompleteManager(this));
		getCommand("reload").setExecutor(new reload());
		getCommand("reload").setTabCompleter(new onTabCompleteManager(this));
		getCommand("whitelist").setExecutor(new whitelist());
		getCommand("whitelist").setTabCompleter(new onTabCompleteManager(this));
		getCommand("warp").setExecutor(new warp());
		getCommand("warp").setTabCompleter(new onTabCompleteManager(this));
		getCommand("skull").setExecutor(new skull());
		getCommand("skull").setTabCompleter(new onTabCompleteManager(this));
		getCommand("spawnmob").setExecutor(new spawnmob());
		getCommand("spawnmob").setTabCompleter(new onTabCompleteManager(this));
		getCommand("spawnpoint").setExecutor(new spawnpoint());
		getCommand("spawnpoint").setTabCompleter(new onTabCompleteManager(this));
		getCommand("effect").setExecutor(new effect());
		getCommand("effect").setTabCompleter(new onTabCompleteManager(this));
		getCommand("asConsole").setExecutor(new asConsole());
		getCommand("asConsole").setTabCompleter(new onTabCompleteManager(this));
		getCommand("tpall").setExecutor(new tpall());
		getCommand("tpall").setTabCompleter(new onTabCompleteManager(this));
		getCommand("tempban").setExecutor(new tempban(this));
		getCommand("tempban").setTabCompleter(new onTabCompleteManager(this));
		getCommand("vanish").setExecutor(new vanish());
		getCommand("vanish").setTabCompleter(new onTabCompleteManager(this));
//		getCommand("chunkloader").setExecutor(new chunkloader());
//		getCommand("chunkloader").setTabCompleter(new onTabCompleteManager(this));
		getCommand("chat").setExecutor(new chat());
		getCommand("chat").setTabCompleter(new onTabCompleteManager(this));
		//Register Listeners
		Bukkit.getPluginManager().registerEvents(new EGListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PhysicExplosionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new Signs(), this);
		Bukkit.getPluginManager().registerEvents(new logger(this), this);
		Bukkit.getPluginManager().registerEvents(new ChunkLoaderListener(), this);
		//ReloadGroupPrefix
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				getEssentialsGreenManager().ReloadGroupPrefix();
			}
		}, 0, 5);
		//Start Metrics
		try{
	        metrics = new Metrics(this);
	        metrics.start();
	        System.out.println(prefix + getEssentialsGreenManager().getMessageManager().getMessage("MetricsStart"));
	    }catch(IOException e){
	        System.err.println(prefix + getEssentialsGreenManager().getMessageManager().getMessage("MetricsStartFailed"));
	    }
		//Chunkloader
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Chunk c : getEssentialsGreenManager().getChunkLoaderManager().getChunks()){
					YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/EssentialsGreen/config.yml"));
					if(config.getString("ChunkLoaderEnable").equalsIgnoreCase("true")){
						if(c.isLoaded() == false){
							c.load(true);
						}
					}
				}
				
				for(Block b : getEssentialsGreenManager().getChunkLoaderManager().getChunkLoaders()){
					YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/EssentialsGreen/config.yml"));
					b.setType(Material.matchMaterial(config.getString("ChunkLoaderBlock")));
				}
			}
		}, 20*60, 20);
		System.out.println(prefix + getEssentialsGreenManager().getMessageManager().getMessage("load"));
	}
	
	@Override
	public void onDisable(){
		System.out.println(getEssentialsGreenManager().getMessageManager().getMessage("isdisable"));
		try{
			metrics.disable();
			System.out.println(getEssentialsGreenManager().getMessageManager().getMessage("MetricsStop"));
		}catch(IOException e){
			System.out.println(prefix + getEssentialsGreenManager().getMessageManager().getMessage("MetricsStopFailed"));
			e.printStackTrace();
		}
		System.out.println(getEssentialsGreenManager().getMessageManager().getMessage("disable"));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(cmd.getName().equalsIgnoreCase("EssentialsGreen")){
			if(args.length == 0){
				sender.sendMessage(prefix + "By Marco MC | [Marco606598]\n§3"
						+ "/eg info\n"
						+ "/eg reload");
			}else{
				if(args[0].equalsIgnoreCase("info")){
					sender.sendMessage(prefix + "§a\n"
							+ "Name: " + getDescription().getName() + "\n"
							+ "Version: " + getDescription().getVersion() + "\n"
							+ "Authors: " + getDescription().getAuthors());
				}else if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("EssentialsGreen.reload")){
						reloadConfig();
						sender.sendMessage("§7Config reload!");
						SpawnF = new File("plugins/EssentialsGreen/Spawn.yml");
						SpawnYaml = YamlConfiguration.loadConfiguration(SpawnF);
						sender.sendMessage("§7Spawn Config reload!");
						getEssentialsGreenManager().getMessageManager().reload();
						sender.sendMessage("§7Message Config reload!");
						getEssentialsGreenManager().getChunkLoaderManager().reload();
						sender.sendMessage(prefix + "§7ChunkLoader reload!");
						sender.sendMessage(prefix + "Reload completed");
					}else sender.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("nopermissions"));
				}else sender.sendMessage(EssentialsGreen.prefix + "§4[§lError§r§4] Unknown SubCommand : '§e" + args[0] + "§4'");
			}
		}
		return true;
	}

	/* This Methode is a API for Developer. */
	public static EssentialsGreenManager getEssentialsGreenManager(){
		return EGAPI.getAPI();
	}
}