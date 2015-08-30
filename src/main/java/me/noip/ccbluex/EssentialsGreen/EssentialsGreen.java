package me.noip.ccbluex.EssentialsGreen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import me.noip.ccbluex.EssentialsGreen.CommandManager.onTabCompleteManager;
import me.noip.ccbluex.EssentialsGreen.Commands.asConsole;
import me.noip.ccbluex.EssentialsGreen.Commands.ban;
import me.noip.ccbluex.EssentialsGreen.Commands.banlist;
import me.noip.ccbluex.EssentialsGreen.Commands.broadcast;
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
import me.noip.ccbluex.EssentialsGreen.Listeners.ExplosionListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.LogListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.MainListener;
import me.noip.ccbluex.EssentialsGreen.Listeners.Signs;
import me.noip.ccbluex.EssentialsGreen.util.EssentialsGreenManager;
import me.noip.ccbluex.EssentialsGreen.util.InternetAPI;
import me.noip.ccbluex.EssentialsGreen.util.Results;
import me.noip.ccbluex.EssentialsGreen.util.Warp;
import me.noip.ccbluex.EssentialsGreen.util.WarpManager;

public class EssentialsGreen extends JavaPlugin implements CommandExecutor {

	public static String prefix = "§2[EG]§e ";
	public File SpawnF;
	public YamlConfiguration SpawnYaml;
	public static String name;
	public static String version;

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
		//Register Listeners
		Bukkit.getPluginManager().registerEvents(new MainListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ExplosionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new Signs(), this);
		Bukkit.getPluginManager().registerEvents(new LogListener(), this);
		//Information
		name = getDescription().getName();
		version = getDescription().getVersion();
		//Start Metrics
		try{
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	        System.out.println("[EssentialsGreen] Metrics start!");
	    }catch(IOException e){
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
				SFAGroup();
			}
		}, 0, 5);
		//AutoUpdater
		String AutoUpdateString = null;
		String[] File = InternetAPI.ReadURL("https://www.dropbox.com/s/p2h0a0umvwmcmy5/Info.txt?dl=1").split(",");
		if(Double.parseDouble(File[0]) > new Float(getDescription().getVersion())){
			AutoUpdateString = "[EssentialsGreen] New Version Avaible";
			if(getConfig().getString("AutoUpdate").equalsIgnoreCase("true")){
				AutoUpdateString = "[EssentialsGreen] The new version is in Downloading!";
				try{
					InternetAPI.downloadFile(File[1], "plugins/EssentialsGreen.jar");
						Bukkit.reload();
				}catch(IllegalStateException | IOException e){
					e.printStackTrace();
				}
			}
		}else{
			AutoUpdateString = "[EssentialsGreen] No New Version Avaible";
		}
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
	
	/*
	 * This Methode controled the Prefix
	 */
	public void SFAGroup(){
		for(Player p : Bukkit.getOnlinePlayers()){
			File File = new File("plugins/EssentialsGreen/userdata/" + p.getUniqueId().toString() + ".data");
			YamlConfiguration YF = YamlConfiguration.loadConfiguration(File);
			try{
				Class.forName("ru.tehkode.permissions.bukkit.PermissionsEx");
			    YF.set("GroupPrefix", ru.tehkode.permissions.bukkit.PermissionsEx.getUser(p).getPrefix());
			    try{
			    	YF.save(File);
			    }catch(IOException e2){
			    	e2.printStackTrace();
			    }
			}catch(ClassNotFoundException e){
				YF.set("GroupPrefix", "");
				try{
			    	YF.save(File);
			    }catch(IOException e2){
			    	e.printStackTrace();
			    }
			}
			
		}
	}
	
	/* This Methode is a API for Developer. */
	public static EssentialsGreenManager getEssentialsGreenManager(){
		EssentialsGreenManager manage = new EssentialsGreenManager() {
			
			@Override
			public String getName() {
				return EssentialsGreen.name;
			}
			
			@Override
			public String getVersion() {
				return EssentialsGreen.version;
			}

			@Override
			public WarpManager getWarpManager() {
				WarpManager wmanage = new WarpManager() {
					@Override
					public Results removeWarp(String Name) {
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Name + ".yml");
						if(WarpFile.delete() == false) return Results.File_can_not_delete;
						return Results.Success;
					}
					
					@Override
					public Collection<Warp> getWarps() {
						Collection<Warp> Warps = new ArrayList<Warp>();
						for(File file : new File("plugins/EssentialsGreen/Warp").listFiles()){
							final String Warp = YamlConfiguration.loadConfiguration(file).getString("Name");
							Warp warp = new Warp() {
								
								@Override
								public Results setName(String Name) {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									WarpYaml.set("Name", Name);
									try{
										WarpYaml.save(WarpFile);
									}catch (IOException e) {
										e.printStackTrace();
										return  Results.File_can_not_save;
									}
									return Results.Success;
								}
								
								@SuppressWarnings("unused")
								@Override
								public Results setLocation(double x, double y, double z, Float yaw, Float pitch, String World) {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									if(Bukkit.getWorld(World) != null){
										Location loc = new Location(Bukkit.getWorld(World), x, y, z, yaw, pitch);
										if(loc != null){
											WarpYaml.set("Location.X", x);
											WarpYaml.set("Location.Y", y);
											WarpYaml.set("Location.Z", z);
											WarpYaml.set("Location.Yaw", yaw);
											WarpYaml.set("Location.Pitch", pitch);
											WarpYaml.set("Location.World", World);
											try{
												WarpYaml.save(WarpFile);
											}catch (IOException e) {
												e.printStackTrace();
												return  Results.File_can_not_save;
											}
										}else return Results.New_Location_ERROR;
									}else return Results.World_exist_not;
									return Results.Success;
								}
								
								@Override
								public Results setLocation(Location loc) {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									WarpYaml.set("Location.X", loc.getX());
									WarpYaml.set("Location.Y", loc.getY());
									WarpYaml.set("Location.Z", loc.getZ());
									WarpYaml.set("Location.Yaw", loc.getYaw());
									WarpYaml.set("Location.Pitch", loc.getPitch());
									WarpYaml.set("Location.World", loc.getWorld().getName());
									try{
										WarpYaml.save(WarpFile);
									}catch (IOException e) {
										e.printStackTrace();
										return  Results.File_can_not_save;
									}
									return Results.Success;
								}
								
								@Override
								public UUID getUUID() {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									return UUID.fromString(WarpYaml.getString("UUID"));
								}
								
								@Override
								public String getName() {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									return WarpYaml.getString("Name");
								}
								
								@Override
								public Location getLocation() {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration File = YamlConfiguration.loadConfiguration(WarpFile);
									World world = Bukkit.getWorld(File.getString("Location.World"));
									if(world != null){
										Location loc = new Location(world, File.getDouble("Location.X"), File.getDouble("Location.Y"), File.getDouble("Location.Z"), new Float(File.getString("Location.Yaw")), new Float(File.getString("Location.Pitch")));
										return loc;
									}
									return null;
								}
							};
							Warps.add(warp);
						}
						return Warps;
					}
					
					@Override
					public Warp getWarp(final String Warp) {
						if(new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml").exists()){
							Warp warp = new Warp() {
								
								@Override
								public Results setName(String Name) {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									WarpYaml.set("Name", Name);
									try{
										WarpYaml.save(WarpFile);
									}catch (IOException e) {
										e.printStackTrace();
										return  Results.File_can_not_save;
									}
									return Results.Success;
								}
								
								@SuppressWarnings("unused")
								@Override
								public Results setLocation(double x, double y, double z, Float yaw, Float pitch, String World) {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									if(Bukkit.getWorld(World) != null){
										Location loc = new Location(Bukkit.getWorld(World), x, y, z, yaw, pitch);
										if(loc != null){
											WarpYaml.set("Location.X", x);
											WarpYaml.set("Location.Y", y);
											WarpYaml.set("Location.Z", z);
											WarpYaml.set("Location.Yaw", yaw);
											WarpYaml.set("Location.Pitch", pitch);
											WarpYaml.set("Location.World", World);
											try{
												WarpYaml.save(WarpFile);
											}catch (IOException e) {
												e.printStackTrace();
												return  Results.File_can_not_save;
											}
										}else return Results.New_Location_ERROR;
									}else return Results.World_exist_not;
									return Results.Success;
								}
								
								@Override
								public Results setLocation(Location loc) {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									WarpYaml.set("Location.X", loc.getX());
									WarpYaml.set("Location.Y", loc.getY());
									WarpYaml.set("Location.Z", loc.getZ());
									WarpYaml.set("Location.Yaw", loc.getYaw());
									WarpYaml.set("Location.Pitch", loc.getPitch());
									WarpYaml.set("Location.World", loc.getWorld().getName());
									try{
										WarpYaml.save(WarpFile);
									}catch (IOException e) {
										e.printStackTrace();
										return  Results.File_can_not_save;
									}
									return Results.Success;
								}
								
								@Override
								public UUID getUUID() {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									return UUID.fromString(WarpYaml.getString("UUID"));
								}
								
								@Override
								public String getName() {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
									return WarpYaml.getString("Name");
								}
								
								@Override
								public Location getLocation() {
									File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
									YamlConfiguration File = YamlConfiguration.loadConfiguration(WarpFile);
									World world = Bukkit.getWorld(File.getString("Location.World"));
									if(world != null){
										Location loc = new Location(world, File.getDouble("Location.X"), File.getDouble("Location.Y"), File.getDouble("Location.Z"), new Float(File.getString("Location.Yaw")), new Float(File.getString("Location.Pitch")));
										return loc;
									}
									return null;
								}
							};
							return warp;
						}
						return null;
					}
					
					@Override
					public Warp getWarp(UUID uuid) {
						for(File file : new File("plugins/EssentialsGreen/Warp").listFiles()){
							YamlConfiguration warpyml = YamlConfiguration.loadConfiguration(file);
							if(UUID.fromString(YamlConfiguration.loadConfiguration(file).getString("")) == uuid){
								final String Warp = warpyml.getName();
								Warp warp = new Warp() {
									
									@Override
									public Results setName(String Name) {
										File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
										YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
										WarpYaml.set("Name", Name);
										try{
											WarpYaml.save(WarpFile);
										}catch (IOException e) {
											e.printStackTrace();
											return  Results.File_can_not_save;
										}
										return Results.Success;
									}
									
									@SuppressWarnings("unused")
									@Override
									public Results setLocation(double x, double y, double z, Float yaw, Float pitch, String World) {
										File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
										YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
										if(Bukkit.getWorld(World) != null){
											Location loc = new Location(Bukkit.getWorld(World), x, y, z, yaw, pitch);
											if(loc != null){
												WarpYaml.set("Location.X", x);
												WarpYaml.set("Location.Y", y);
												WarpYaml.set("Location.Z", z);
												WarpYaml.set("Location.Yaw", yaw);
												WarpYaml.set("Location.Pitch", pitch);
												WarpYaml.set("Location.World", World);
												try{
													WarpYaml.save(WarpFile);
												}catch (IOException e) {
													e.printStackTrace();
													return  Results.File_can_not_save;
												}
											}else return Results.New_Location_ERROR;
										}else return Results.World_exist_not;
										return Results.Success;
									}
									
									@Override
									public Results setLocation(Location loc) {
										File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
										YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
										WarpYaml.set("Location.X", loc.getX());
										WarpYaml.set("Location.Y", loc.getY());
										WarpYaml.set("Location.Z", loc.getZ());
										WarpYaml.set("Location.Yaw", loc.getYaw());
										WarpYaml.set("Location.Pitch", loc.getPitch());
										WarpYaml.set("Location.World", loc.getWorld().getName());
										try{
											WarpYaml.save(WarpFile);
										}catch (IOException e) {
											e.printStackTrace();
											return  Results.File_can_not_save;
										}
										return Results.Success;
									}
									
									@Override
									public UUID getUUID() {
										File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
										YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
										return UUID.fromString(WarpYaml.getString("UUID"));
									}
									
									@Override
									public String getName() {
										File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
										YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
										return WarpYaml.getString("Name");
									}
									
									@Override
									public Location getLocation() {
										File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Warp + ".yml");
										YamlConfiguration File = YamlConfiguration.loadConfiguration(WarpFile);
										World world = Bukkit.getWorld(File.getString("Location.World"));
										if(world != null){
											Location loc = new Location(world, File.getDouble("Location.X"), File.getDouble("Location.Y"), File.getDouble("Location.Z"), new Float(File.getString("Location.Yaw")), new Float(File.getString("Location.Pitch")));
											return loc;
										}
										return null;
									}
								};
								return warp;
							}
						}
						return null;
					}
					
					@Override
					public boolean contains(String Name){
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Name + ".yml");
						return WarpFile.exists();
					}
					
					@Override
					public Results addWarp(String Name, double x, double y, double z, Float yaw, Float pitch, String World){
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Name + ".yml");
						if(!WarpFile.exists()){
							YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
							WarpYaml.set("Name", Name);
							WarpYaml.set("UUID", UUID.randomUUID());
							WarpYaml.set("Location.X", x);
							WarpYaml.set("Location.Y", y);
							WarpYaml.set("Location.Z", z);
							WarpYaml.set("Location.Yaw", yaw);
							WarpYaml.set("Location.Pitch", pitch);
							WarpYaml.set("Location.World", World);
							try{
								WarpYaml.save(WarpFile);
							}catch (IOException e){
								e.printStackTrace();
								return Results.File_can_not_save;
							}
							return Results.Success;
						}else return Results.File_exist;
					}
					
					@Override
					public Results addWarp(String Name, Location loc) {
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Name + ".yml");
						if(!WarpFile.exists()){
							YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
							WarpYaml.set("Name", Name);
							WarpYaml.set("UUID", UUID.randomUUID());
							WarpYaml.set("Location.X", loc.getX());
							WarpYaml.set("Location.Y", loc.getY());
							WarpYaml.set("Location.Z", loc.getZ());
							WarpYaml.set("Location.Yaw", loc.getYaw());
							WarpYaml.set("Location.Pitch", loc.getPitch());
							WarpYaml.set("Location.World", loc.getWorld().getName());
							try{
								WarpYaml.save(WarpFile);
							}catch (IOException e){
								e.printStackTrace();
								return Results.File_can_not_save;
							}
							return Results.Success;
						}else return Results.File_exist;
					}
				};
				return wmanage;
			}
		};
		return manage;
	}
}