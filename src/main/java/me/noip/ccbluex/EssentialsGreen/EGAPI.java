/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.common.io.Files;

import me.noip.ccbluex.EssentialsGreen.APIs.NetworkAPI;
import me.noip.ccbluex.EssentialsGreen.Annotation.Working;
import me.noip.ccbluex.EssentialsGreen.Player.User;
import me.noip.ccbluex.EssentialsGreen.managers.ChatManager;
import me.noip.ccbluex.EssentialsGreen.managers.EssentialsGreenManager;
import me.noip.ccbluex.EssentialsGreen.managers.FileManager;
import me.noip.ccbluex.EssentialsGreen.managers.MessageManager;
import me.noip.ccbluex.EssentialsGreen.managers.UpdateManager;
import me.noip.ccbluex.EssentialsGreen.managers.UserManager;
import me.noip.ccbluex.EssentialsGreen.managers.WarpManager;
import me.noip.ccbluex.EssentialsGreen.util.Ban;
import me.noip.ccbluex.EssentialsGreen.util.BanType;
import me.noip.ccbluex.EssentialsGreen.util.Message;
import me.noip.ccbluex.EssentialsGreen.util.Results;
import me.noip.ccbluex.EssentialsGreen.util.Warp;

/**
 * @author Marco
 *
 */
@SuppressWarnings("deprecation")
public class EGAPI {

	public static EssentialsGreenManager getAPI() {
		EssentialsGreenManager manage = new EssentialsGreenManager() {
			
			@Override
			public String getName() {
				return EssentialsGreen.maintance.getDescription().getName();
			}
			
			@Override
			public String getVersion() {
				return EssentialsGreen.maintance.getDescription().getVersion();
			}

			@Override
			public WarpManager getWarpManager() {
				WarpManager wmanage = new WarpManager() {
					@Override
					public Results removeWarp(String Name) {
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + Name + ".yml");
						if(WarpFile.delete() == false){
							return Results.File_can_not_delete;
						}
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
							WarpYaml.set("UUID", UUID.randomUUID().toString());
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
							WarpYaml.set("UUID", UUID.randomUUID().toString());
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

			@Override
			public void ReloadGroupPrefix() {
				for(Player p : Bukkit.getOnlinePlayers()){
					File File = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
					YamlConfiguration YF = YamlConfiguration.loadConfiguration(File);
					try{
						Class.forName("ru.tehkode.permissions.bukkit.PermissionsEx");
					    YF.set("GroupPrefix", ru.tehkode.permissions.bukkit.PermissionsEx.getUser(p).getPrefix());
					    try{
					    	YF.save(File);
					    }catch(IOException e){
					    	e.printStackTrace();
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

			@Override
			public UpdateManager getUpdateManager() throws MalformedURLException {
				UpdateManager upma = new UpdateManager() {
					
					private String urlegver = "https://www.dropbox.com/s/ubbldsa53qsoify/version.eg?dl=1";
					private String[] egversionfile;
					double version;
					String url;
					
					@Override
					public boolean updateNeeded() {
						if(version < Double.parseDouble(EssentialsGreen.getEssentialsGreenManager().getVersion())){
							return true;
						}
						return false;
					}

					@Override
					public void downloadlatestfile(String output) throws IllegalStateException, MalformedURLException, ProtocolException, IOException {
						NetworkAPI.downloadFile(url, output);
					}

					@Override
					public void load() {
						egversionfile = NetworkAPI.ReadURL(urlegver).split(",");
						version = Double.parseDouble(egversionfile[0]);
						url = egversionfile[1];
					}

					@Override
					public void reload() {
						load();
					}
				};
				return upma;
			}

			@Override
			public MessageManager getMessageManager() {
				MessageManager mm = new MessageManager() {
					
					File messagefile;
					YamlConfiguration messageyaml;
					
					@Override
					public Message getMessage(String path) {
						final String YamlMSG = ChatColor.translateAlternateColorCodes('&', messageyaml.getString(path));
						
						Message msg = new Message() {
							String Message = YamlMSG;
							@Override
							public String toString(){
								return Message;
							}
						};
						return msg;
					}
					
					@Override
					public void setMessage(String message, String path) {
						messageyaml.set(path, message);
					}
					
					@Override
					public void reload() {
						load();
					}
					
					@Override
					public void create(){
						EssentialsGreen.maintance.saveResource("message.yml", true);
						reload();
					}
					
					@Override
					public void save() throws IOException {
						messageyaml.save(messagefile);
					}

					@Override
					public void createifnotexist() {
						EssentialsGreen.maintance.saveResource("message.yml", false);
						reload();
					}

					@Override
					public void load() {
						messageyaml = getFileManager().getYaml("plugins/EssentialsGreen/message.yml");
						messagefile = getFileManager().getFile("plugins/EssentialsGreen/message.yml");
					}
				};
				return mm;
			}

			@Override
			public FileManager getFileManager() {
				FileManager filemanager = new FileManager() {
					
					@Override
					public InputStream getResource(String filename, Class<?> Class) throws FileNotFoundException {
						InputStream is = Class.getResourceAsStream("/" + filename);
						return is;
					}
					
					@Override
					public File getFile(String path) {
						return new File(path);
					}
					
					@Override
					public void copyfile(InputStream from, File to) throws IOException {
					    OutputStream os = null;
					    try{
					        os = new FileOutputStream(to);
					        byte[] buffer = new byte[1024];
					        int length;
					        while ((length = from.read(buffer)) > 0) {
					            os.write(buffer, 0, length);
					        }
					    }finally{
					        from.close();
					        os.close();
					    }
					}
					
					@Override
					public void copyfile(File from, File to) throws IOException {
						Files.copy(from, to);
					}

					@Override
					public YamlConfiguration getYaml(String path) {
						return YamlConfiguration.loadConfiguration(new File(path));
					}
				};
				return filemanager;
			}

			@Override
			public ChatManager getChatManager() {
				ChatManager cm = new ChatManager() {
					
					private String chatclear = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ";
					
					@Override
					public boolean send(Collection<Player> p, String message) {
						if(message == null){message = "";}
						if(p != null){
							int l = 0;
							for(Player pp : p){
								pp.sendMessage(message);
								l = l+1;
							}
							//Check to send all messages on all players out the list.
							if(l == p.size()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public boolean send(Player p, String message) {
						if(message == null){message = "";}
						if(p != null){
							p.sendMessage(message);
							return true;
						}
						return false;
					}
					
					@Override
					public void mute(Collection<? extends Player> p, boolean mute) throws IOException {
						for(Player op : p){
							getUserManager().getUser(op.getName()).setMute(mute);
						}
					}
					
					@Override
					public void mute(Player p, boolean mute) throws IOException {
						getUserManager().getUser(p.getName()).setMute(mute);
					}
					
					@Override
					public boolean clear(Collection<Player> p) {
						if(p != null){
							int l = 0;
							for(Player pp : p){
								pp.sendMessage(chatclear);
								l = l+1;
							}
							//Check to send all messages on all players out the list.
							if(l == p.size()){
								return true;
							}
						}
						return false;
					}
					
					@Override
					public boolean clear(Player p) {
						if(p != null){
							p.sendMessage(chatclear);
							return true;
						}
						return false;
					}
				};
				return cm;
			}

			@Override
			public UserManager getUserManager(){
				UserManager um = new  UserManager() {
					
					@Override
					public User createUser(Player p, boolean isNew) throws IOException {
						if(p != null){
							YamlConfiguration defaultyaml = YamlConfiguration.loadConfiguration(getFileManager().getResource("uuid.user", getClass()));
							defaultyaml.set("Username", p.getName());
							defaultyaml.set("IsNewPlayer", isNew);
							defaultyaml.set("IP", p.getAddress().getAddress().toString());
							defaultyaml.set("Port", p.getAddress().getPort());
							File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
							defaultyaml.save(userfile);
							return getUser(p.getName());
						}
						return null;
					}
					
					@Override
					public User getUser(String name){
						return getUser(Bukkit.getOfflinePlayer(name).getUniqueId());
					}
					
					@Override
					public User getUser(UUID uuid) {
						final OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
						
						User user = new User() {
							
							@Override
							public void setMute(boolean mute) throws IOException {
								File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								useryaml.set("Mute.Enable", mute);
								useryaml.save(userfile);
							}
							
							@Override
							public boolean isMute(){
								File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								return useryaml.getBoolean("Mute.Enable");
							}
							
							@Override
							public UUID getUUID() {
								return op.getUniqueId();
							}
							
							@Override
							public Player getPlayer() {
								return op.getPlayer();
							}
							
							@Override
							public OfflinePlayer getOfflinePlayer() {
								return op;
							}
								
							@Override
							public InetSocketAddress getAddress() {
								return getPlayer().getAddress();
							}
							
							
							@Override
							public Ban getBan(){
								Ban ban = new Ban() {
									
									@Override
									public void setBan(boolean ban, String reason, String author, Date date) throws IOException {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										//Check values!
										if(reason == null) reason = "";
										if(author == null) author = "";
										if(date == null) date = new Date();
										//Set all options for the player!
										op.setBanned(ban);
										setReason(reason);
										setAuthor(author);
										setDate(date);
										setType(BanType.Perma);
										//Kick the player when online!
										if(op instanceof Player){
											getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("Ban-Message") + "\n§fAuthor: §e" + getAuthor() + " §fDate: §e" + getDate() + "\n§fReason: §7" + getReason()));
										}
										//Save the user file!
										try{
											useryaml.save(userfile);
										}catch(IOException e){
											e.printStackTrace();
										}
									}
									
									@Override
									public void setReason(String reason) throws IOException {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										useryaml.set("Ban.Reason", reason);
										useryaml.save(userfile);
									}
									
									@Override
									public void setDate(Date date) throws IOException {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
										useryaml.set("Ban.Date", time.format(date));
										useryaml.save(userfile);
									}
									
									@Override
									public void setAuthor(String user) throws IOException {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										useryaml.set("Ban.Author", user);
										useryaml.save(userfile);
									}
									

									@Override
									public void setType(BanType type) throws IOException {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										useryaml.set("Ban.Type", type.name());
										useryaml.save(userfile);
									}
									
									@Override
									public String getReason() {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										return useryaml.getString("Ban.Reason");
									}
									
									@Override
									public String getDate() {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										return useryaml.getString("Ban.Date");
									}
									
									@Override
									public String getAuthor() {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										return useryaml.getString("Ban.Author");
									}

									@Override
									public BanType getType() {
										File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
										YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
										return BanType.of(useryaml.getString("Ban.Type"));
									}
									
									@Override
									public boolean isBanned() {
										return op.isBanned();
									}

									@Working
									@Override
									public void setTempBan(boolean ban, String reason, String author, Date date, int years, int months, int days, int minutes, int seconds) {
									}
									
								};
								return ban;
							}

							@Override
							public void UpdateUserFile() throws IOException {
								File userfile = new File("plugins/EssentialsGreen/users/" + op.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								useryaml.set("Username", op.getName());
								Player p = getPlayer();
								if(p != null){
									useryaml.set("IP", p.getAddress().getAddress().toString());
									useryaml.set("Port", p.getAddress().getPort());
								}
								useryaml.save(userfile);
							}
						};
						return user;
					}

					@Override
					public boolean existUser(String name) {
						File F = new File("plugins/EssentialsGreen/users");
						File[] list = F.listFiles();
						for(int i = 0; i < list.length; i++){
							YamlConfiguration Player = YamlConfiguration.loadConfiguration(list[i]);
							if(Player.getString("Username").equalsIgnoreCase(name)){
								return true;
							}
						}
						return false;
					}

					@Override
					public boolean existUser(UUID uuid) {
						return new File("plugins/EssentialsGreen/users/" + uuid.toString() + ".user").exists();
					}
				};
				return um;
			}
		};
		return manage;
	}
}