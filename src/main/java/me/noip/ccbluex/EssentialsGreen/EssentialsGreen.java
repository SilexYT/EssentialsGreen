package me.noip.ccbluex.EssentialsGreen;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import me.noip.ccbluex.EssentialsGreen.CommandManager.onTabCompleteManager;
import me.noip.ccbluex.EssentialsGreen.Commands.asConsole;
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
import me.noip.ccbluex.EssentialsGreen.util.Message;

public class EssentialsGreen extends JavaPlugin {

	/* The maintance as static to load easy the maintance */
	public static EssentialsGreen maintance;
	/* Logger */
	Logger log;
	/* The prefix of the plugin */
	public static String prefix;
	/* Save here the loaded metrics of the EssentialsGreen plugin*/
	Metrics metrics;
	/* Here save the Spawn file */
	public File SpawnFile;
	public YamlConfiguration SpawnYaml;
	/* MessageYaml is here extra loaded! */
	private YamlConfiguration message;
	
	@Override
	public void onLoad(){
		/* Load the maintance! */{
			maintance = this;
		}
		
		/* Say to the logger the server is starting! */
		logger.startserver();
		/* Create the files! */{
			//File: plugins/EssentialsGreen/config.yml
			saveDefaultConfig();
			//File: plugins/EssentialsGreen/message.yml / extra load!
			getEssentialsGreenManager().getMessageManager().createifnotexist();
			message = getEssentialsGreenManager().getFileManager().getYaml("plugins/EssentialsGreen/message.yml");
			//Load prefix
			prefix = getMessage("prefix").toString() + " ";
			//File: plugins/EssentialsGreen/spawn.yml
			saveResource("spawn.yml", false);
			//File: plugins/EssentialsGreen/users
			File UserdataFile = new File("plugins/EssentialsGreen/users");
			UserdataFile.mkdir();
			//File: plugins/EssentialsGreen/Warp
			File WarpFile = new File("plugins/EssentialsGreen/Warp");
			WarpFile.mkdir();
			//File: plugins/EssentialsGreen/backups
			File backupFile = new File("plugins/EssentialsGreen/backups");
			backupFile.mkdir();
		}
	}
	
	@Override
	public void onEnable(){
		log.info(prefix + getMessage("isenable"));
		//Is files toooo old?
		isfilestoold();
		//Register: Commands
		registerCommands();
		//Register: Listeners
		registerListeners();
		//Register: Reload-Group-Prefix Scheudular
		groupprefixscheud();
		//Start Metrics
		startMetrics();
		//INFO: Is Update avaible?
		Thread autoup = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					EssentialsGreen.getEssentialsGreenManager().getUpdateManager().load();
					if(EssentialsGreen.getEssentialsGreenManager().getUpdateManager().updateNeeded()){
						log.info(prefix + getMessage("NewVersion"));
						log.info(prefix + getMessage("Updateisdown"));
						EssentialsGreen.getEssentialsGreenManager().getUpdateManager().downloadlatestfile("plugins/EssentialsGreen.jar");
						log.info(prefix + getMessage("Updateisfinish"));
					}else{
						log.info(prefix + getMessage("NoNewVersion"));
					}
				}catch(Exception e){
					log.info(prefix + getMessage("NoInternetConnection"));
				}
			}
		});
		autoup.start();
		log.info(prefix + getMessage("enable"));
	}
	
	@Override
	public void onDisable(){
		log.info(prefix + getMessage("isdisable"));
		/* Say to the logger the server is stopping! */
		logger.stopserver();
		/* Stop Metrics */
		stopMetrics();
		log.info(prefix + getMessage("disable"));
	}

	private void registerCommands(){
		//Commands
		getCommand("tp").setExecutor(new tp());
		getCommand("tp").setTabCompleter(new onTabCompleteManager());
		getCommand("gamemode").setExecutor(new gamemode());
		getCommand("gamemode").setTabCompleter(new onTabCompleteManager());
		getCommand("setspawn").setExecutor(new setspawn());
		getCommand("setspawn").setTabCompleter(new onTabCompleteManager());
		getCommand("spawn").setExecutor(new spawn());
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
//		getCommand("tempban").setExecutor(new tempban(this));
//		getCommand("tempban").setTabCompleter(new onTabCompleteManager());
		getCommand("vanish").setExecutor(new vanish());
		getCommand("vanish").setTabCompleter(new onTabCompleteManager());
		getCommand("chat").setExecutor(new chat());
		getCommand("chat").setTabCompleter(new onTabCompleteManager());
//		getCommand("backup").setExecutor(new backup());
//		getCommand("backup").setTabCompleter(new onTabCompleteManager());
		getCommand("servermanage").setExecutor(new servermanage());
		getCommand("servermanage").setTabCompleter(new onTabCompleteManager());
	}
	
	private void registerListeners(){
		Bukkit.getPluginManager().registerEvents(new EGListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PhysicExplosionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new Signs(), this);
		Bukkit.getPluginManager().registerEvents(new logger(), this);
	}
	
	private void isfilestoold(){
		//Is config toooo old?
		if(Double.parseDouble(getEssentialsGreenManager().getVersion()) > getConfig().getDouble("ConfigVersion")){
			File oldc = getEssentialsGreenManager().getFileManager().getFile("plugins/EssentialsGreen/config.yml");
			try{
				getEssentialsGreenManager().getFileManager().copyfile(oldc, new File("plugins/EssentialsGreen/oldconfig.yml"));
			}catch(IOException e){
				e.printStackTrace();
			}
			oldc.delete();
			saveDefaultConfig();
		}
		//Is Message toooo old?
		File messagef = EssentialsGreen.getEssentialsGreenManager().getFileManager().getFile("plugins/EssentialsGreen/message.yml");
		YamlConfiguration messageyaml = EssentialsGreen.getEssentialsGreenManager().getFileManager().getYaml("plugins/EssentialsGreen/message.yml");
		if(Double.parseDouble(getEssentialsGreenManager().getVersion()) > messageyaml.getDouble("MessageVersion")){
			try {
				getEssentialsGreenManager().getFileManager().copyfile(messagef, new File("plugins/EssentialsGreen/oldmessage.yml"));
			}catch (IOException e){
				e.printStackTrace();
			}
			getEssentialsGreenManager().getMessageManager().create();
			messageyaml = getEssentialsGreenManager().getFileManager().getYaml("plugins/EssentialsGreen/message.yml");
		}
	}
	
	private void groupprefixscheud(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				getEssentialsGreenManager().ReloadGroupPrefix();
			}
		}, 0, 1);
	}
	
	public void startMetrics(){
		try{
	        metrics = new Metrics(this);
	        if(metrics.isOptOut()){
	        	metrics.start();
		        System.out.println(prefix + getMessage("MetricsStart"));
	        }else{
		        throw new Exception();
	        }
	    }catch(Exception e){
	        System.err.println(prefix + getMessage("MetricsStartFailed"));
	    }
	}
	
	public void stopMetrics(){
		try{
			metrics.disable();
			System.out.println(prefix + getMessage("MetricsStop"));
		}catch(Exception e){
			System.err.println(prefix + getMessage("MetricsStopFailed"));
			e.printStackTrace();
		}
	}
	
	/* This methode is important the getMessage api is by load buggy! */
	private Message getMessage(final String pathmess){
		return new Message(){
			String message = ChatColor.translateAlternateColorCodes('&', EssentialsGreen.maintance.message.getString(pathmess));
			
			@Override
			public String toString() {
				return message;
			}
			
		};
	}
	
	//Default: Command
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(cmd.getName().equalsIgnoreCase("EssentialsGreen")){
			if(args.length == 0){
				sender.sendMessage(prefix + " [COMMANDS]\n�3"
						+ "/eg info\n"
						+ "/eg reload");
			}else{
				if(args[0].equalsIgnoreCase("info")){
					sender.sendMessage(prefix + "�a\n"
							+ "Name: " + getDescription().getName() + "\n"
							+ "Version: " + getDescription().getVersion() + "\n"
							+ "Authors: " + getDescription().getAuthors() + ""
					);
				}else if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("EssentialsGreen.reload")){
						reloadConfig();
						sender.sendMessage("�7Config reload!");
						SpawnFile = new File("plugins/EssentialsGreen/Spawn.yml");
						SpawnYaml = YamlConfiguration.loadConfiguration(SpawnFile);
						sender.sendMessage("�7Spawn Config reload!");
						getEssentialsGreenManager().getMessageManager().reload();
						message = getEssentialsGreenManager().getFileManager().getYaml("plugins/EssentialsGreen/message.yml");
						sender.sendMessage("�7Message Config reload!");
						sender.sendMessage(prefix + "Reload completed");
					}else sender.sendMessage(prefix + getMessage("nopermissions"));
				}else sender.sendMessage(prefix + "�4[�lError�r�4] Unknown SubCommand : '�e" + args[0] + "�4'");
			}
		}
		return true;
	}
	
	/* This Methode is a API for Developer. */
	public static EssentialsGreenManager getEssentialsGreenManager(){
		return EGAPI.getAPI();
	}
}