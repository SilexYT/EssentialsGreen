package gg.web.mcb.EssentialsGreen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import gg.web.mcb.EssentialsGreen.API.InternetAPI;
import gg.web.mcb.EssentialsGreen.API.ItemManagerAPI;
import gg.web.mcb.EssentialsGreen.API.JavaAPI;
import gg.web.mcb.EssentialsGreen.API.Metrics;
import gg.web.mcb.EssentialsGreen.API.StringAPI;
import gg.web.mcb.EssentialsGreen.API.TablistTitleAPI;
import gg.web.mcb.EssentialsGreen.API.TitleAPI;
import gg.web.mcb.EssentialsGreen.CommandFiles.actionbar;
import gg.web.mcb.EssentialsGreen.CommandFiles.Ban;
import gg.web.mcb.EssentialsGreen.CommandFiles.banlist;
import gg.web.mcb.EssentialsGreen.CommandFiles.broadcast;
import gg.web.mcb.EssentialsGreen.CommandFiles.clear;
import gg.web.mcb.EssentialsGreen.CommandFiles.defaultgamemode;
import gg.web.mcb.EssentialsGreen.CommandFiles.effect;
import gg.web.mcb.EssentialsGreen.CommandFiles.fly;
import gg.web.mcb.EssentialsGreen.CommandFiles.gamemode;
import gg.web.mcb.EssentialsGreen.CommandFiles.give;
import gg.web.mcb.EssentialsGreen.CommandFiles.heal;
import gg.web.mcb.EssentialsGreen.CommandFiles.invsee;
import gg.web.mcb.EssentialsGreen.CommandFiles.kick;
import gg.web.mcb.EssentialsGreen.CommandFiles.kill;
import gg.web.mcb.EssentialsGreen.CommandFiles.list;
import gg.web.mcb.EssentialsGreen.CommandFiles.msg;
import gg.web.mcb.EssentialsGreen.CommandFiles.nick;
import gg.web.mcb.EssentialsGreen.CommandFiles.say;
import gg.web.mcb.EssentialsGreen.CommandFiles.seed;
import gg.web.mcb.EssentialsGreen.CommandFiles.setspawn;
import gg.web.mcb.EssentialsGreen.CommandFiles.setworldspawn;
import gg.web.mcb.EssentialsGreen.CommandFiles.skull;
import gg.web.mcb.EssentialsGreen.CommandFiles.spawn;
import gg.web.mcb.EssentialsGreen.CommandFiles.spawnmob;
import gg.web.mcb.EssentialsGreen.CommandFiles.spawnpoint;
import gg.web.mcb.EssentialsGreen.CommandFiles.speed;
import gg.web.mcb.EssentialsGreen.CommandFiles.time;
import gg.web.mcb.EssentialsGreen.CommandFiles.tp;
import gg.web.mcb.EssentialsGreen.CommandFiles.unban;
import gg.web.mcb.EssentialsGreen.CommandFiles.warp;
import gg.web.mcb.EssentialsGreen.CommandFiles.whitelist;
import gg.web.mcb.EssentialsGreen.CommandFiles.xp;
import gg.web.mcb.EssentialsGreen.ListenerFiles.ExplosionListener;
import gg.web.mcb.EssentialsGreen.ListenerFiles.LogListener;
import gg.web.mcb.EssentialsGreen.ListenerFiles.MainListener;
import gg.web.mcb.EssentialsGreen.ListenerFiles.Signs;
import gg.web.mcb.EssentialsGreen.ServerManageCommandFiles.Reload;
import gg.web.mcb.EssentialsGreen.ServerManageCommandFiles.Stop;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class EssentialsGreen extends JavaPlugin implements CommandExecutor {

	public static String prefix = "§2[EG]§e ";
	public File SpawnF;
	public YamlConfiguration SpawnYaml;

	@Override
	public void onEnable(){
		//Register Commands
		getCommand("tp").setExecutor(new tp());
		getCommand("gm").setExecutor(new gamemode());
		getCommand("Gamemode").setExecutor(new gamemode());
		getCommand("SetSpawn").setExecutor(new setspawn(this));
		getCommand("Spawn").setExecutor(new spawn(this));
		getCommand("kick").setExecutor(new kick(this));
		getCommand("ban").setExecutor(new Ban(this));
		getCommand("unban").setExecutor(new unban(this));
		getCommand("banlist").setExecutor(new banlist());
		getCommand("give").setExecutor(new give());
		getCommand("msg").setExecutor(new msg());
		getCommand("time").setExecutor(new time());
		getCommand("XP").setExecutor(new xp());
		getCommand("broadcast").setExecutor(new broadcast());
		getCommand("say").setExecutor(new say());
		getCommand("kill").setExecutor(new kill());
		getCommand("fly").setExecutor(new fly());
		getCommand("speed").setExecutor(new speed());
		getCommand("invsee").setExecutor(new invsee());
		getCommand("heal").setExecutor(new heal());
		getCommand("nick").setExecutor(new nick(this));
		getCommand("setworldspawn").setExecutor(new setworldspawn());
		getCommand("seed").setExecutor(new seed());
		getCommand("clear").setExecutor(new clear());
		getCommand("list").setExecutor(new list());
		getCommand("defaultgamemode").setExecutor(new defaultgamemode());
		getCommand("stop").setExecutor(new Stop());
		getCommand("reload").setExecutor(new Reload());
		getCommand("rl").setExecutor(new Reload());
		getCommand("whitelist").setExecutor(new whitelist());
		getCommand("warp").setExecutor(new warp());
		getCommand("skull").setExecutor(new skull());
		getCommand("spawnmob").setExecutor(new spawnmob());
		getCommand("spawnpoint").setExecutor(new spawnpoint());
		getCommand("effect").setExecutor(new effect());
		getCommand("actionbar").setExecutor(new actionbar());
		//Register Listeners
		Bukkit.getPluginManager().registerEvents(new MainListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ExplosionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new Signs(), this);
		Bukkit.getPluginManager().registerEvents(new LogListener(), this);
		//StartMetrics
		try{
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	        System.out.println("[EssentialsGreen] Metrics start!");
	    }catch (IOException e) {
	        System.err.println("[EssentialsGreen] Metrics start failed!");
	    }
		//ConfigFile
		saveDefaultConfig();
		//SpawnFile
		SpawnF = new File("plugins/EssentialsGreen/Spawn.yml");
		SpawnYaml = YamlConfiguration.loadConfiguration(SpawnF);
		try{SpawnYaml.save(SpawnF);}catch (IOException e){e.printStackTrace();}
		//UserdateFile
		File UserdataFile = new File("plugins/EssentialsGreen/userdata");
		UserdataFile.mkdir();
		//WarpFile
		File WarpFile = new File("plugins/EssentialsGreen/Warp");
		WarpFile.mkdir();
		//ReloadGroupPrefix
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				SetforAllPlayerGroupPrefix();
			}
		}, 0, 20);
		Collection<Object> APIs = new ArrayList<Object>();
		APIs.add(new JavaAPI());
		APIs.add(new InternetAPI());
		APIs.add(new ItemManagerAPI());
		APIs.add(new StringAPI());
		APIs.add(new TitleAPI());
		APIs.add(new TablistTitleAPI());
		JavaAPI.RegisterAPIs(APIs);
		//DownloadAPIs
		loadAPIs();
		//AutoUpdater
		String AutoUpdateString = null;
		YamlConfiguration Dec = YamlConfiguration.loadConfiguration(getResource("plugin.yml"));
		if(Dec.getString("IsDevBuild").equalsIgnoreCase("false")){
			String[] File = InternetAPI.ReadURL("https://www.dropbox.com/s/p2h0a0umvwmcmy5/Info.txt?dl=1").split(",");
			if(File[0].contains(getDescription().getVersion())){
				AutoUpdateString = "[EssentialsGreen] No New Version Avaible";
			}else{
				AutoUpdateString = "[EssentialsGreen] New Version Avaible";
				if(getConfig().getString("AutoUpdate").equalsIgnoreCase("true")){
					AutoUpdateString = "[EssentialsGreen] The new version is in Downloading...\nBy the restart is the new version in runnning!";
					try{
						InternetAPI.downloadFile(File[1], "plugins/EssentialsGreen.jar");
						Bukkit.reload();
					}catch(IllegalStateException | IOException e){
						e.printStackTrace();
					}
				}
			}
		}else AutoUpdateString = "[EssentialsGreen] Disable AutoUpdater rampart this version a Developer Version is!";
		System.out.println(AutoUpdateString);
		System.out.println("[EssentialsGreen] Load Completed");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(cmd.getName().equalsIgnoreCase("eg") | cmd.getName().equalsIgnoreCase("EssentialsGreen")){
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
						SpawnF = new File("plugins/EssentialsGreen/Spawn.yml");
						SpawnYaml = YamlConfiguration.loadConfiguration(SpawnF);
						sender.sendMessage(prefix + "Config Reload completed");
					}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
				}
			}
		}
		return true;
	}
	
	public void SetforAllPlayerGroupPrefix(){
		for(Player p : Bukkit.getOnlinePlayers()){
			File File = new File("plugins/EssentialsGreen/userdata/" + p.getUniqueId().toString() + ".data");
			YamlConfiguration YF = YamlConfiguration.loadConfiguration(File);
			if(Bukkit.getPluginCommand("pex") != null){
				YF.set("GroupPrefix", ru.tehkode.permissions.bukkit.PermissionsEx.getUser(p).getPrefix());
			}else YF.set("GroupPrefix", "");
			try{YF.save(File);}catch(IOException e){e.printStackTrace();}
		}
	}
	
	public void loadAPIs(){
		//APIs Download
		File NickNamer = new File("plugins/NickNamer_v2.2.3.jar");
		File PacketListener = new File("plugins/PacketListenerAPI_v2.5.2.jar");
		if(!NickNamer.exists() & !PacketListener.exists()){
			try{
				InternetAPI.downloadFile("https://www.dropbox.com/s/vz8shvl5mybuiax/NickNamer_v2.2.3.jar?dl=1", NickNamer.getPath());
				InternetAPI.downloadFile("https://www.dropbox.com/s/blfseig9payc1fx/PacketListenerApi_v2.5.2.jar?dl=1", PacketListener.getPath());
				Bukkit.reload();
			}catch (IllegalStateException | IOException e){
				System.out.println("[EssentialsGreen] The APIs can not download... please check you internet");
			}
		}
	}
}