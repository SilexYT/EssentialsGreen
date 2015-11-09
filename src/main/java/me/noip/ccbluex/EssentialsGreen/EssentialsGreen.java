package me.noip.ccbluex.EssentialsGreen;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;
import org.xml.sax.SAXException;

import me.noip.ccbluex.EssentialsGreen.CommandManager.onTabCompleteManager;
import me.noip.ccbluex.EssentialsGreen.Commands.asConsole;
import me.noip.ccbluex.EssentialsGreen.Commands.backup;
import me.noip.ccbluex.EssentialsGreen.Commands.ban;
import me.noip.ccbluex.EssentialsGreen.Commands.banlist;
import me.noip.ccbluex.EssentialsGreen.Commands.broadcast;
import me.noip.ccbluex.EssentialsGreen.Commands.chat;
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
import me.noip.ccbluex.EssentialsGreen.Commands.say;
import me.noip.ccbluex.EssentialsGreen.Commands.seed;
import me.noip.ccbluex.EssentialsGreen.Commands.servermanage;
import me.noip.ccbluex.EssentialsGreen.Commands.setspawn;
import me.noip.ccbluex.EssentialsGreen.Commands.setworldspawn;
import me.noip.ccbluex.EssentialsGreen.Commands.skull;
import me.noip.ccbluex.EssentialsGreen.Commands.spawn;
import me.noip.ccbluex.EssentialsGreen.Commands.spawnmob;
import me.noip.ccbluex.EssentialsGreen.Commands.spawnpoint;
import me.noip.ccbluex.EssentialsGreen.Commands.speed;
import me.noip.ccbluex.EssentialsGreen.Commands.tempban;
import me.noip.ccbluex.EssentialsGreen.Commands.time;
import me.noip.ccbluex.EssentialsGreen.Commands.tp;
import me.noip.ccbluex.EssentialsGreen.Commands.tpall;
import me.noip.ccbluex.EssentialsGreen.Commands.unban;
import me.noip.ccbluex.EssentialsGreen.Commands.vanish;
import me.noip.ccbluex.EssentialsGreen.Commands.warp;
import me.noip.ccbluex.EssentialsGreen.Commands.whitelist;
import me.noip.ccbluex.EssentialsGreen.Commands.xp;
import me.noip.ccbluex.EssentialsGreen.Listeners.logger;
import me.noip.ccbluex.EssentialsGreen.Listeners.EGListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.PhysicExplosionListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.Signs;
import me.noip.ccbluex.EssentialsGreen.managers.EssentialsGreenManager;

public class EssentialsGreen extends JavaPlugin {

	public static EssentialsGreen maintance;
	public static String prefix;
	public File SpawnF;
	public YamlConfiguration SpawnYaml;
	Metrics metrics;
	FileConfiguration conf;

	@Override
	public void onLoad() {
		maintance = this;
		//File
		File egfile = new File("plugins/EssentialsGreen");
		egfile.mkdir();
		//Userdate File
		File UserdataFile = new File("plugins/EssentialsGreen/users");
		UserdataFile.mkdir();
		//Warp File
		File WarpFile = new File("plugins/EssentialsGreen/Warp");
		WarpFile.mkdir();
		//Backups file
		File backupFile = new File("plugins/EssentialsGreen/backups");
		backupFile.mkdir();
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
		SpawnF = new File("plugins/EssentialsGreen/spawn.yml");
		SpawnYaml = YamlConfiguration.loadConfiguration(SpawnF);
		try{
			SpawnYaml.save(SpawnF);
		}catch (IOException e){
			e.printStackTrace();
		}
		//CheckUpdates!
		try{
			getEssentialsGreenManager().getUpdateManager().REGISTERXML();
			if(getEssentialsGreenManager().getUpdateManager().updateNeeded()){
				System.out.println(prefix + messageyaml.getString("NewVersion").replace('&', '§'));
				Bukkit.broadcastMessage(prefix + messageyaml.getString("NewVersion").replace('&', '§'));
			}else System.out.println(prefix + messageyaml.getString("NoNewVersion").replace('&', '§'));
		}catch(IOException | SAXException | ParserConfigurationException e1){
			e1.printStackTrace();
		}
		System.out.println(prefix + messageyaml.getString("isload").replace('&', '§'));
	}
	
	@Override
	public void onEnable(){
		getCommand("tp").setExecutor(new tp());
		getCommand("tp").setTabCompleter(new onTabCompleteManager());
		getCommand("gamemode").setExecutor(new gamemode());
		getCommand("gamemode").setTabCompleter(new onTabCompleteManager());
		getCommand("setspawn").setExecutor(new setspawn(this));
		getCommand("setspawn").setTabCompleter(new onTabCompleteManager());
		getCommand("spawn").setExecutor(new spawn(this));
		getCommand("spawn").setTabCompleter(new onTabCompleteManager());
		getCommand("kick").setExecutor(new kick());
		getCommand("kick").setTabCompleter(new onTabCompleteManager());
		getCommand("ban").setExecutor(new ban(this));
		getCommand("ban").setTabCompleter(new onTabCompleteManager());
		getCommand("unban").setExecutor(new unban(this));
		getCommand("unban").setTabCompleter(new onTabCompleteManager());
		getCommand("banlist").setExecutor(new banlist());
		getCommand("banlist").setTabCompleter(new onTabCompleteManager());
		getCommand("give").setExecutor(new give());
		getCommand("give").setTabCompleter(new onTabCompleteManager());
		getCommand("msg").setExecutor(new msg());
		getCommand("msg").setTabCompleter(new onTabCompleteManager());
		getCommand("time").setExecutor(new time());
		getCommand("time").setTabCompleter(new onTabCompleteManager());
		getCommand("xp").setExecutor(new xp());
		getCommand("xp").setTabCompleter(new onTabCompleteManager());
		getCommand("broadcast").setExecutor(new broadcast());
		getCommand("broadcast").setTabCompleter(new onTabCompleteManager());
		getCommand("say").setExecutor(new say());
		getCommand("say").setTabCompleter(new onTabCompleteManager());
		getCommand("kill").setExecutor(new kill());
		getCommand("kill").setTabCompleter(new onTabCompleteManager());
		getCommand("fly").setExecutor(new fly());
		getCommand("fly").setTabCompleter(new onTabCompleteManager());
		getCommand("speed").setExecutor(new speed());
		getCommand("speed").setTabCompleter(new onTabCompleteManager());
		getCommand("invsee").setExecutor(new invsee());
		getCommand("invsee").setTabCompleter(new onTabCompleteManager());
		getCommand("heal").setExecutor(new heal());
		getCommand("heal").setTabCompleter(new onTabCompleteManager());
		getCommand("nick").setExecutor(new nick(this));
		getCommand("nick").setTabCompleter(new onTabCompleteManager());
		getCommand("setworldspawn").setExecutor(new setworldspawn());
		getCommand("setworldspawn").setTabCompleter(new onTabCompleteManager());
		getCommand("seed").setExecutor(new seed());
		getCommand("seed").setTabCompleter(new onTabCompleteManager());
		getCommand("clear").setExecutor(new clear());
		getCommand("clear").setTabCompleter(new onTabCompleteManager());
		getCommand("list").setExecutor(new list());
		getCommand("list").setTabCompleter(new onTabCompleteManager());
		getCommand("defaultgamemode").setExecutor(new defaultgamemode());
		getCommand("defaultgamemode").setTabCompleter(new onTabCompleteManager());
		getCommand("whitelist").setExecutor(new whitelist());
		getCommand("whitelist").setTabCompleter(new onTabCompleteManager());
		getCommand("warp").setExecutor(new warp());
		getCommand("warp").setTabCompleter(new onTabCompleteManager());
		getCommand("skull").setExecutor(new skull());
		getCommand("skull").setTabCompleter(new onTabCompleteManager());
		getCommand("spawnmob").setExecutor(new spawnmob());
		getCommand("spawnmob").setTabCompleter(new onTabCompleteManager());
		getCommand("spawnpoint").setExecutor(new spawnpoint());
		getCommand("spawnpoint").setTabCompleter(new onTabCompleteManager());
		getCommand("effect").setExecutor(new effect());
		getCommand("effect").setTabCompleter(new onTabCompleteManager());
		getCommand("asConsole").setExecutor(new asConsole());
		getCommand("asConsole").setTabCompleter(new onTabCompleteManager());
		getCommand("tpall").setExecutor(new tpall());
		getCommand("tpall").setTabCompleter(new onTabCompleteManager());
		getCommand("tempban").setExecutor(new tempban(this));
		getCommand("tempban").setTabCompleter(new onTabCompleteManager());
		getCommand("vanish").setExecutor(new vanish());
		getCommand("vanish").setTabCompleter(new onTabCompleteManager());
		getCommand("chat").setExecutor(new chat());
		getCommand("chat").setTabCompleter(new onTabCompleteManager());
		getCommand("backup").setExecutor(new backup());
		getCommand("backup").setTabCompleter(new onTabCompleteManager());
		getCommand("servermanage").setExecutor(new servermanage());
		getCommand("servermanage").setTabCompleter(new onTabCompleteManager());
		//Register Listeners
		Bukkit.getPluginManager().registerEvents(new EGListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PhysicExplosionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new Signs(), this);
		Bukkit.getPluginManager().registerEvents(new logger(this), this);
		//ReloadGroupPrefix
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				getEssentialsGreenManager().ReloadGroupPrefix();
			}
		}, 0, 1);
		//Start Metrics
		try{
	        metrics = new Metrics(this);
	        if(metrics.isOptOut()){
	        	metrics.start();
		        System.out.println(prefix + getEssentialsGreenManager().getMessageManager().getMessage("MetricsStart"));
	        }else{
	        	System.err.println(prefix + getEssentialsGreenManager().getMessageManager().getMessage("MetricsStartFailed"));
	        }
	    }catch(Exception e){
	        System.err.println(prefix + getEssentialsGreenManager().getMessageManager().getMessage("MetricsStartFailed"));
	    }
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
				sender.sendMessage(prefix + " [COMMAND'S]\n§3"
						+ "/eg info\n"
						+ "/eg reload");
			}else{
				if(args[0].equalsIgnoreCase("info")){
					String update;
//						getEssentialsGreenManager().getUpdateManager().REGISTERXML();
//						if(getEssentialsGreenManager().getUpdateManager().updateNeeded()){
//							update = getEssentialsGreenManager()
//									.getMessageManager()
//									.getMessage("NewVersion")
//									.toString()
//									.replace("{version}", String.valueOf(getEssentialsGreenManager().getUpdateManager().getVersion()))
//									.replace("{link}", getEssentialsGreenManager().getUpdateManager().getLink());
//							update = "New version avaible!";
//						}else{
//							update = getEssentialsGreenManager().getMessageManager().getMessage("NoNewVersion").toString();
//						}
					update = "Working...";
					//Output
					sender.sendMessage(prefix + "§a\n"
							+ "Name: " + getDescription().getName() + "\n"
							+ "Version: " + getDescription().getVersion() + "\n"
							+ "Authors: " + getDescription().getAuthors() + "\n"
							+ "UPDATE: " + update + ""
					);
				}else if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("EssentialsGreen.reload")){
						reloadConfig();
						sender.sendMessage("§7Config reload!");
						SpawnF = new File("plugins/EssentialsGreen/Spawn.yml");
						SpawnYaml = YamlConfiguration.loadConfiguration(SpawnF);
						sender.sendMessage("§7Spawn Config reload!");
						getEssentialsGreenManager().getMessageManager().reload();
						sender.sendMessage("§7Message Config reload!");
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