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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.common.io.Files;

import me.noip.ccbluex.EssentialsGreen.APIs.InternetAPI;
import me.noip.ccbluex.EssentialsGreen.Player.User;
import me.noip.ccbluex.EssentialsGreen.managers.ChatManager;
import me.noip.ccbluex.EssentialsGreen.managers.ChunkLoaderManager;
import me.noip.ccbluex.EssentialsGreen.managers.EssentialsGreenManager;
import me.noip.ccbluex.EssentialsGreen.managers.FileManager;
import me.noip.ccbluex.EssentialsGreen.managers.MessageManager;
import me.noip.ccbluex.EssentialsGreen.managers.UpdateManager;
import me.noip.ccbluex.EssentialsGreen.managers.UserManager;
import me.noip.ccbluex.EssentialsGreen.managers.WarpManager;
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
				InputStream is = getClass().getResourceAsStream("/plugin.yml");
				return YamlConfiguration.loadConfiguration(is).getString("name");
			}
			
			@Override
			public String getVersion() {
				InputStream is = getClass().getResourceAsStream("/plugin.yml");
				return YamlConfiguration.loadConfiguration(is).getString("version");
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

			@Override
			public UpdateManager getUpdateManager() {
				UpdateManager upma = new UpdateManager() {
					
					String URL = "https://www.dropbox.com/s/oxrl6e1pbnc77y0/version.eg?dl=1";
					
					@Override
					public Results downloadUpdate(String OUTPATH, boolean reload) {
						String[] File = getFile().split(",");
						try{
							InternetAPI.downloadFile(File[1], "plugins/EssentialsGreen.jar");
							if(reload){
								Bukkit.reload(); 
							}
							return Results.Download;
						}catch(IllegalStateException | IOException e){
							e.printStackTrace();
							return Results.No_Internet;
						}
					}
					
					@Override
					public boolean checkUpdate() {
						//AutoUpdater
						String[] File = getFile().split(",");
						if(Double.parseDouble(File[0]) > Double.parseDouble(getVersion())){
							return true;
						}
						return false;
					}

					@Override
					public String getFile() {
						String File = InternetAPI.ReadURL(URL);
						return File;
					}
				};
				return upma;
			}

			@Override
			public MessageManager getMessageManager() {
				MessageManager mm = new MessageManager() {
					
					File file = new File("plugins/EssentialsGreen/message.yml");
					YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
					
					@Override
					public Message getMessage(String path) {
						final String YamlMSG = yaml.getString(path).replace('&', '§');
						
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
					public void setMessage(String message, String path) throws IOException {
						yaml.set(path, message);
						save();
					}
					
					@Override
					public void reload() {
						file = new File("plugins/EssentialsGreen/message.yml");
						yaml = YamlConfiguration.loadConfiguration(file);
					}
					
					@Override
					public void load() throws IOException {
						System.out.println("[EssentialsGreen] Message.yml is in create!");
						InputStream is = getClass().getResourceAsStream("/message.yml");
						FileOutputStream os = new FileOutputStream(file);
						for(int read = 0; (read = is.read()) != -1;){
							os.write(read);
						}
						os.flush();
						os.close();
						is.close();
						reload();
						System.out.println("[EssentialsGreen] Message.yml is create!");
					}
					
					@Override
					public void save() throws IOException {
						yaml.save(file);
					}
				};
				return mm;
			}

			@Override
			public ChunkLoaderManager getChunkLoaderManager(){
				ChunkLoaderManager cm = new ChunkLoaderManager() {
					
					YamlConfiguration yaml;
					
					@Override
					public Chunk addChunkLoader(Location loc) throws IOException {
						YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/EssentialsGreen/config.yml"));
						removeChunkLoader(loc);
						Collection<String> c = yaml.getStringList("cl");
						c.add(loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getWorld().getName());
						yaml.set("cl", c);
						yaml.save(new File("plugins/EssentialsGreen/chunkloader.yml"));
						loc.getBlock().setType(Material.matchMaterial(config.getString("ChunkLoaderBlock")));
						return loc.getChunk();
					}

					@Override
					public boolean removeChunkLoader(Location loc) throws IOException {
						Collection<String> c = yaml.getStringList("cl");
						Collection<Chunk> chu = getChunks();
						for(int i = 0; chu.size() > i; i++){
							Chunk[] chunks = (Chunk[])chu.toArray();
							if(chunks[i] == loc.getChunk()){
								c.remove(i);
								yaml.set("ChunkLoaders", c);
								return true;
							}
						}
						yaml.set("cl", c);
						yaml.save(new File("plugins/EssentialsGreen/chunkloader.yml"));
						return false;
					}

					@Override
					public void info(Location loc, Player getsinfo) {
						Chunk ch = loc.getChunk();
						getsinfo.sendMessage("§eChunk Infos:\n"
								+ "§eX§7:§e " + ch.getX() + "\n"
								+ "§eZ§7:§e " + ch.getZ() + "\n"
								+ "§eWorld§7:§e " + ch.getWorld().getName());
					}

					@Override
					public void reload() {
						File chunkloaderfile = new File("plugins/EssentialsGreen/chunkloader.yml");
						yaml = YamlConfiguration.loadConfiguration(chunkloaderfile);
					}

					@Override
					public Collection<Chunk> getChunks() {
						reload();
						Collection<Chunk> chunks = new ArrayList<Chunk>();
						Collection<String> c = yaml.getStringList("cl");
						for(String s : c){
							String[] st = s.split(",");
							chunks.add(new Location(Bukkit.getWorld(st[3]), Double.parseDouble(st[0]), Double.parseDouble(st[1]), Double.parseDouble(st[2])).getChunk());
						}
						return chunks;
					}

					@Override
					public Collection<Block> getChunkLoaders() {
						reload();
						Collection<Block> block = new ArrayList<Block>();
						Collection<String> c = yaml.getStringList("cl");
						for(String s : c){
							String[] st = s.split(",");
							block.add(new Location(Bukkit.getWorld(st[3]), Double.parseDouble(st[0]), Double.parseDouble(st[1]), Double.parseDouble(st[2])).getBlock());
						}
						return block;
					}

					@Override
					public void start() throws FileNotFoundException, IOException {
						File file = new File("plugins/EssentialsGreen/chunkloader.yml");
						if(file.exists()){
							yaml = YamlConfiguration.loadConfiguration(file);
						}else{
							getFileManager().copyfile(getFileManager().getResource("chunkloader.yml", getClass()), file);
							file = new File("plugins/EssentialsGreen/chunkloader.yml");
							yaml = YamlConfiguration.loadConfiguration(file);
						}
					}

				};
				return cm;
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
					public void mute(Collection<Player> p, boolean mute) throws IOException {
						for(Player pf : p){
							getUserManager().getUser(pf).setMute(mute);
						}
					}
					
					@Override
					public void mute(Player p, boolean mute) throws IOException {
						getUserManager().getUser(p).setMute(mute);
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
					public User createUser(final Player p, boolean isNew) throws IOException {
						if(p != null){
							YamlConfiguration defaultyaml = YamlConfiguration.loadConfiguration(getFileManager().getResource("uuid.user", getClass()));
							defaultyaml.set("Username", p.getName());
							defaultyaml.set("IsNewPlayer", isNew);
							defaultyaml.set("IP", p.getAddress().getAddress().toString());
							defaultyaml.set("Port", p.getAddress().getPort());
							Date date = new Date();
						    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
						    defaultyaml.set("firstjoin", time.format(date));
							File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
							defaultyaml.save(userfile);
							User user = new User() {
								
								@Override
								public void setMute(boolean mute) throws IOException {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Mute.Enable", mute);
									useryaml.save(userfile);
								}
								
								@Override
								public boolean isMute() {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									return useryaml.getBoolean("Mute.Enable");
								}
								
								@Override
								public UUID getUUID() {
									return p.getUniqueId();
								}
								
								@Override
								public Player getPlayer() {
									return p;
								}
								
								@Override
								public OfflinePlayer getOfflinePlayer() {
									return Bukkit.getOfflinePlayer(p.getName());
								}
								
								@Override
								public InetSocketAddress getAddress() {
									return p.getAddress();
								}

								@Override
								public void setBan(boolean ban, String reason, String author) {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Ban.Enable", ban);
									useryaml.set("Ban.Type", "Ban");
									useryaml.set("Ban.Reason", reason);
									useryaml.set("Ban.Author", author);
									Date date = new Date();
								    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
								    useryaml.set("Ban.date", time.format(date));

									if(p instanceof Player){
										p.kickPlayer(ChatColor.translateAlternateColorCodes('&', getMessageManager().getMessage("Ban-Message").toString() + "\n§fAuthor: §e" + useryaml.getString("Ban.Author") + " §fDate: §e" + useryaml.getString("Ban.date") + "\n§fExpires in §e" + useryaml.getString("Ban.Ex") + " §fSeconds" + "\n§fReason: §7" + useryaml.getString("Ban.Reason")));
									}
									try{
										useryaml.save(userfile);
									}catch(IOException e){
										e.printStackTrace();
									}
								}

								@Override
								public boolean isBan() {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									return useryaml.getBoolean("Ban.Enable");
								}

								@Deprecated
								@Override
								public void setTempBanWorking(boolean ban, String reason, String author) {
									
								}

								@Override
								public void UpdateUserFile() throws IOException {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Username", p.getName());
									useryaml.set("IP", p.getAddress().getAddress().toString());
									useryaml.set("Port", p.getAddress().getPort());
									Date date = new Date();
								    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
								    useryaml.set("lastjoin", time.format(date));
									useryaml.save(userfile);
								}
							};
							return user;
						}
						return null;
					}

					@Override
					public User getUser(final Player p) {
						if(p != null){
							User user = new User() {
								
								@Override
								public void setMute(boolean mute) throws IOException {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Mute.Enable", mute);
									useryaml.save(userfile);
								}
								
								@Override
								public boolean isMute() {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									return useryaml.getBoolean("Mute.Enable");
								}
								
								@Override
								public UUID getUUID() {
									return p.getUniqueId();
								}
								
								@Override
								public Player getPlayer() {
									return p;
								}
								
								@Override
								public OfflinePlayer getOfflinePlayer() {
									return Bukkit.getOfflinePlayer(p.getName());
								}
								
								@Override
								public InetSocketAddress getAddress() {
									return p.getAddress();
								}

								@Override
								public void setBan(boolean ban, String reason, String author) {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Ban.Enable", ban);
									useryaml.set("Ban.Type", "Ban");
									useryaml.set("Ban.Reason", reason);
									useryaml.set("Ban.Author", author);
									Date date = new Date();
								    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
								    useryaml.set("Ban.date", time.format(date));

									if(p instanceof Player){
										p.kickPlayer(ChatColor.translateAlternateColorCodes('&', getMessageManager().getMessage("Ban-Message").toString() + "\n§fAuthor: §e" + useryaml.getString("Ban.Author") + " §fDate: §e" + useryaml.getString("Ban.date") + "\n§fExpires in §e" + useryaml.getString("Ban.Ex") + " §fSeconds" + "\n§fReason: §7" + useryaml.getString("Ban.Reason")));
									}
									try{
										useryaml.save(userfile);
									}catch(IOException e){
										e.printStackTrace();
									}
								}

								@Override
								public boolean isBan() {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									return useryaml.getBoolean("Ban.Enable");
								}

								@Deprecated
								@Override
								public void setTempBanWorking(boolean ban, String reason, String author) {
									
								}

								@Override
								public void UpdateUserFile() throws IOException {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Username", p.getName());
									useryaml.set("IP", p.getAddress().getAddress().toString());
									useryaml.set("Port", p.getAddress().getPort());
									useryaml.save(userfile);
								}
							};
							return user;
						}
						return null;
					}
					
					@Override
					public User getUser(String name){
						Player pn = Bukkit.getPlayer(name);
						if(pn != null){
							pn = (Player)Bukkit.getOfflinePlayer(name);
						}
						
						final Player p = pn;
						
						if(p != null){
							User user = new User() {
								
								@Override
								public void setMute(boolean mute) throws IOException {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Mute.Enable", mute);
									useryaml.save(userfile);
								}
								
								@Override
								public boolean isMute() {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									return useryaml.getBoolean("Mute.Enable");
								}
								
								@Override
								public UUID getUUID() {
									return p.getUniqueId();
								}
								
								@Override
								public Player getPlayer() {
									return p;
								}
								
								@Override
								public OfflinePlayer getOfflinePlayer() {
									return Bukkit.getOfflinePlayer(p.getName());
								}
								
								@Override
								public InetSocketAddress getAddress() {
									return p.getAddress();
								}

								@Override
								public void setBan(boolean ban, String reason, String author) {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Ban.Enable", ban);
									useryaml.set("Ban.Type", "Ban");
									useryaml.set("Ban.Reason", reason);
									useryaml.set("Ban.Author", author);
									Date date = new Date();
								    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
								    useryaml.set("Ban.date", time.format(date));

									if(p instanceof Player){
										p.kickPlayer(ChatColor.translateAlternateColorCodes('&', getMessageManager().getMessage("Ban-Message").toString() + "\n§fAuthor: §e" + useryaml.getString("Ban.Author") + " §fDate: §e" + useryaml.getString("Ban.date") + "\n§fExpires in §e" + useryaml.getString("Ban.Ex") + " §fSeconds" + "\n§fReason: §7" + useryaml.getString("Ban.Reason")));
									}
									try{
										useryaml.save(userfile);
									}catch(IOException e){
										e.printStackTrace();
									}
								}

								@Override
								public boolean isBan() {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									return useryaml.getBoolean("Ban.Enable");
								}

								@Deprecated
								@Override
								public void setTempBanWorking(boolean ban, String reason, String author) {
									
								}

								@Override
								public void UpdateUserFile() throws IOException {
									File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
									YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
									useryaml.set("Username", p.getName());
									useryaml.set("IP", p.getAddress().getAddress().toString());
									useryaml.set("Port", p.getAddress().getPort());
									useryaml.save(userfile);
								}
							};
							return user;
						}
						return null;
					}

					@Override
					public User getUser(UUID uuid) {
						Player pn = Bukkit.getPlayer(uuid);
						if(pn != null){
							pn = (Player)Bukkit.getOfflinePlayer(uuid);
						}
						
						final Player p = pn;
						
						User user = new User() {
							
							@Override
							public void setMute(boolean mute) throws IOException {
								File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								useryaml.set("Mute.Enable", mute);
								useryaml.save(userfile);
							}
							
							@Override
							public boolean isMute() {
								File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								return useryaml.getBoolean("Mute.Enable");
							}
							
							@Override
							public UUID getUUID() {
								return p.getUniqueId();
							}
							
							@Override
							public Player getPlayer() {
								return p;
							}
							
							@Override
							public OfflinePlayer getOfflinePlayer() {
								return Bukkit.getOfflinePlayer(p.getName());
							}
								
							@Override
							public InetSocketAddress getAddress() {
								return p.getAddress();
							}
							
							@Override
							public void setBan(boolean ban, String reason, String author) {
								File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								useryaml.set("Ban.Enable", ban);
								useryaml.set("Ban.Type", "Ban");
								useryaml.set("Ban.Reason", reason);
								useryaml.set("Ban.Author", author);
								Date date = new Date();
							    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
							    useryaml.set("Ban.date", time.format(date));
								if(p instanceof Player){
									p.kickPlayer(ChatColor.translateAlternateColorCodes('&', getMessageManager().getMessage("Ban-Message").toString() + "\n§fAuthor: §e" + useryaml.getString("Ban.Author") + " §fDate: §e" + useryaml.getString("Ban.date") + "\n§fExpires in §e" + useryaml.getString("Ban.Ex") + " §fSeconds" + "\n§fReason: §7" + useryaml.getString("Ban.Reason")));
								}
								try{
									useryaml.save(userfile);
								}catch(IOException e){
									e.printStackTrace();
								}
							}

							@Override
							public boolean isBan() {
								File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								boolean ban = false;
								if(useryaml.getString("Ban.Enable").equalsIgnoreCase("true")){
									ban = true;
								}
								return ban;
							}

							@Deprecated
							@Override
							public void setTempBanWorking(boolean ban, String reason, String author) {
								
							}

							@Override
							public void UpdateUserFile() throws IOException {
								File userfile = new File("plugins/EssentialsGreen/users/" + p.getUniqueId().toString() + ".user");
								YamlConfiguration useryaml = YamlConfiguration.loadConfiguration(userfile);
								useryaml.set("Username", p.getName());
								useryaml.set("IP", p.getAddress().getAddress().toString());
								useryaml.set("Port", p.getAddress().getPort());
								useryaml.save(userfile);
							}
						};
						return user;
					}

					@Override
					public boolean existUser(Player p) {
						return new File("plugins/EssentialsGreen/users/" +p.getUniqueId().toString() + ".user").exists();
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