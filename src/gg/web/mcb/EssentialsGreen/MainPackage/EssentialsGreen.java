package gg.web.mcb.EssentialsGreen.MainPackage;

import java.io.File;
import java.io.IOException;
import gg.web.mcb.EssentialsGreen.CommandFiles.ActionBar;
import gg.web.mcb.EssentialsGreen.CommandFiles.Ban;
import gg.web.mcb.EssentialsGreen.CommandFiles.Gamemode;
import gg.web.mcb.EssentialsGreen.CommandFiles.Heal;
import gg.web.mcb.EssentialsGreen.CommandFiles.Kick;
import gg.web.mcb.EssentialsGreen.CommandFiles.SetSpawn;
import gg.web.mcb.EssentialsGreen.CommandFiles.Spawn;
import gg.web.mcb.EssentialsGreen.CommandFiles.Teleport;
import gg.web.mcb.EssentialsGreen.CommandFiles.Time;
import gg.web.mcb.EssentialsGreen.CommandFiles.Unban;
import gg.web.mcb.EssentialsGreen.CommandFiles.XP;
import gg.web.mcb.EssentialsGreen.CommandFiles.banlist;
import gg.web.mcb.EssentialsGreen.CommandFiles.broadcast;
import gg.web.mcb.EssentialsGreen.CommandFiles.fly;
import gg.web.mcb.EssentialsGreen.CommandFiles.give;
import gg.web.mcb.EssentialsGreen.CommandFiles.invsee;
import gg.web.mcb.EssentialsGreen.CommandFiles.kill;
import gg.web.mcb.EssentialsGreen.CommandFiles.msg;
import gg.web.mcb.EssentialsGreen.CommandFiles.nick;
import gg.web.mcb.EssentialsGreen.CommandFiles.say;
import gg.web.mcb.EssentialsGreen.CommandFiles.seed;
import gg.web.mcb.EssentialsGreen.CommandFiles.setworldspawn;
import gg.web.mcb.EssentialsGreen.CommandFiles.speed;
import gg.web.mcb.EssentialsGreen.ListenerFiles.ExplosionListener;
import gg.web.mcb.EssentialsGreen.ListenerFiles.LogListener;
import gg.web.mcb.EssentialsGreen.ListenerFiles.MainListener;
import gg.web.mcb.EssentialsGreen.ListenerFiles.Signs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class EssentialsGreen extends JavaPlugin implements CommandExecutor {
	
	public static String prefix = "§2[EG]§e ";
	public File SpawnF;
	public YamlConfiguration SpawnYaml;
	
	@Override
	public void onEnable(){
		//Register Commands
		getCommand("tp").setExecutor(new Teleport());
		getCommand("gm").setExecutor(new Gamemode());
		getCommand("Gamemode").setExecutor(new Gamemode());
		getCommand("SetSpawn").setExecutor(new SetSpawn(this));
		getCommand("Spawn").setExecutor(new Spawn(this));
		getCommand("actionbar").setExecutor(new ActionBar());
		getCommand("kick").setExecutor(new Kick(this));
		getCommand("ban").setExecutor(new Ban(this));
		getCommand("unban").setExecutor(new Unban(this));
		getCommand("banlist").setExecutor(new banlist());
		getCommand("give").setExecutor(new give());
		getCommand("msg").setExecutor(new msg());
		getCommand("time").setExecutor(new Time());
		getCommand("XP").setExecutor(new XP());
		getCommand("broadcast").setExecutor(new broadcast());
		getCommand("say").setExecutor(new say());
		getCommand("kill").setExecutor(new kill());
		getCommand("fly").setExecutor(new fly());
		getCommand("speed").setExecutor(new speed());
		getCommand("invsee").setExecutor(new invsee());
		getCommand("heal").setExecutor(new Heal());
		getCommand("nick").setExecutor(new nick(this));
		getCommand("setworldspawn").setExecutor(new setworldspawn());
		getCommand("seed").setExecutor(new seed());
		//Register Listeners
		Bukkit.getPluginManager().registerEvents(new MainListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ExplosionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new Signs(), this);
		Bukkit.getPluginManager().registerEvents(new LogListener(), this);
		//CopyDefaultConfig
		saveDefaultConfig();
		//Spawn Config File
		SpawnF = new File("plugins/EssentialsGreen/Spawn.yml");
		SpawnYaml = YamlConfiguration.loadConfiguration(SpawnF);
		try{SpawnYaml.save(SpawnF);}catch (IOException e){e.printStackTrace();}
		System.out.println("[EssentialsGreen] Load Completed");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("eg") | cmd.getName().equalsIgnoreCase("EssentialsGreen")){
			if(args.length == 0){
				sender.sendMessage(prefix + "By Marco MC | [Marco606598]\n§3"
						+ "/eg info\n"
						+ "/eg reload\n");
			}else{
				if(args[0].equalsIgnoreCase("info")){
					sender.sendMessage(prefix + "§a\n"
							+ "Name: " + getDescription().getName() + "\n"
							+ "Version: " + getDescription().getVersion() + "\n"
							+ "Authors: " + getDescription().getAuthors());
				}else if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("EssentialsGreen.reload")){
						reloadConfig();
						sender.sendMessage(prefix + "Reload completed");
					}
				}
			}
		}
		return true;
	}
}