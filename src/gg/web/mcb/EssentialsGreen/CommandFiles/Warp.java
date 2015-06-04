package gg.web.mcb.EssentialsGreen.CommandFiles;

import java.io.File;
import java.io.IOException;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.Warp")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(EssentialsGreen.prefix + " [Warp Manager]\n"
							+ "/warp <Warp>\n"
							+ "/warp add <Name>\n"
							+ "/warp remove <Name>\n"
							+ "/warp list\n");
				}else if(args.length > 0){
					if(args[0].equalsIgnoreCase("add")){
						if(args.length > 1){
							File WarpFile = new File("plugins/EssentialsGreen/Warp/" + args[1] + ".yml");
							if(!WarpFile.exists()){
								YamlConfiguration WarpYaml = YamlConfiguration.loadConfiguration(WarpFile);
								Location loc = p.getLocation();
								WarpYaml.set("Name", args[1]);
								WarpYaml.set("Location.X", loc.getX());
								WarpYaml.set("Location.Y", loc.getY());
								WarpYaml.set("Location.Z", loc.getZ());
								WarpYaml.set("Location.Yaw", loc.getYaw());
								WarpYaml.set("Location.Pitch", loc.getPitch());
								WarpYaml.set("Location.World", loc.getWorld().getName());
								try{WarpYaml.save(WarpFile);}catch (IOException e){e.printStackTrace();}
								p.sendMessage(EssentialsGreen.prefix + "Warp add!");
							}else p.sendMessage(EssentialsGreen.prefix + "This Warp already exists!");
						}else p.sendMessage(EssentialsGreen.prefix + "Please write a WarpName");
					}else if(args[0].equalsIgnoreCase("remove")){
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + args[1] + ".yml");
						if(WarpFile.exists()){
							WarpFile.delete();
							p.sendMessage(EssentialsGreen.prefix + "Warp deleted!");
						}else p.sendMessage(EssentialsGreen.prefix + "This Warp exists not!");
					}else if(args[0].equalsIgnoreCase("list")){
						p.sendMessage(EssentialsGreen.prefix + "Warp List");
						File WarpFile = new File("plugins/EssentialsGreen/Warp");
						File[] Files = WarpFile.listFiles();
						for(int i = 0; i < Files.length; i++){
							p.sendMessage("§e" + YamlConfiguration.loadConfiguration(Files[i]).getString("Name"));
						}
						p.sendMessage("§3" + Files.length + " exist!");
					}else{
						File WarpFile = new File("plugins/EssentialsGreen/Warp/" + args[0] + ".yml");
						if(WarpFile.exists()){
							if(p.hasPermission("EssentialsGreen.Warp." + args[0]) | p.hasPermission("EssentialsGreen.Warp.*")){
								YamlConfiguration File = YamlConfiguration.loadConfiguration(WarpFile);
								Location SpawnLoc = new Location(Bukkit.getWorld(File.getString("Location.World")), File.getDouble("Location.X"), File.getDouble("Location.Y"), File.getDouble("Location.Z"), new Float(File.getString("Location.Yaw")), new Float(File.getString("Location.Pitch")));
								p.teleport(SpawnLoc);
								p.sendMessage(EssentialsGreen.prefix + "Teleport...");
							}else p.sendMessage(EssentialsGreen.prefix + "You do not have the permissions for the Warp!");
						}else p.sendMessage(EssentialsGreen.prefix + "This Warp exist not!");
					}
				}
			}else sender.sendMessage(EssentialsGreen.prefix + "You must be a Player!!");
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}