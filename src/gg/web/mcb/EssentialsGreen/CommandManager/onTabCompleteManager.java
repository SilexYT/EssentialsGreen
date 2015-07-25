package gg.web.mcb.EssentialsGreen.CommandManager;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class onTabCompleteManager extends EssentialsGreen implements TabCompleter {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String Label, String[] args) {
		List<String> list = new ArrayList<String>();
		String command = cmd.getName();
		if(command.equalsIgnoreCase("actionbar")){
			if(args.length == 2){
				list.add("<Message...>");
				return list;
			}
		}else if(command.equalsIgnoreCase("ban")){
			if(args.length == 2){
				list.add("<Reason...>");
				return list;
			}
		}else if(command.equalsIgnoreCase("banlist")){
		}else if(command.equalsIgnoreCase("broadcast")){
			if(args.length == 1){
				list.add("<Message...>");
				return list;
			}
		}else if(command.equalsIgnoreCase("clear")){
		}else if(command.equalsIgnoreCase("defaultgamemode")){
			if(args.length == 1){
				list.add("survival");
				list.add("creative");
				list.add("adventure");
				list.add("spectator");
				return list;
			}
		}else if(command.equalsIgnoreCase("effect")){
			if(args.length == 2){
				List<String> effects = (List<String>)YamlConfiguration.loadConfiguration(getResource("effects.list")).getList("Effects");
				return effects;
			}else if(args.length == 3){
				list.add("30");
				return list;
			}else if(args.length == 4){
				list.add("1");
				return list;
			}
		}else if(command.equalsIgnoreCase("fly")){
		}else if(command.equalsIgnoreCase("gamemode")){
			if(args.length == 1){
				list.add("survival");
				list.add("creative");
				list.add("adventure");
				list.add("spectator");
				return list;
			}
		}else if(command.equalsIgnoreCase("give")){
			if(args.length == 2){
				List<String> Materiels = (List<String>)YamlConfiguration.loadConfiguration(getResource("materiels.list")).getList("Materials");
				return Materiels;
			}else if(args.length == 3){
				list.add("64");
				return list;
			}else if(args.length == 4){
				list.add("0");
				return list;
			}
		}else if(command.equalsIgnoreCase("heal")){
		}else if(command.equalsIgnoreCase("invsee")){
		}else if(command.equalsIgnoreCase("kick")){
			if(args.length == 2){
				list.add("<Reason...>");
				return list;
			}
		}else if(command.equalsIgnoreCase("kill")){
			if(args.length == 2 & args[0].equalsIgnoreCase("@e")){
				for(World w : Bukkit.getWorlds()){
					list.add(w.getName());
				}
				return list;
			}
		}else if(command.equalsIgnoreCase("list")){
		}else if(command.equalsIgnoreCase("msg")){
			if(args.length == 2){
				list.add("<Message...>");
				return list;
			}
		}else if(command.equalsIgnoreCase("nick")){
			if(args.length == 1){
				list.add("<off/nick>");
				return list;
			}
		}else if(command.equalsIgnoreCase("say")){
			if(args.length == 1){
				list.add("<Message...>");
				return list;
			}
		}else if(command.equalsIgnoreCase("seed")){
			if(args.length == 1){
				for(World w : Bukkit.getWorlds()){
					list.add(w.getName());
				}
				return list;
			}
		}else if(command.equalsIgnoreCase("setspawn")){
		}else if(command.equalsIgnoreCase("setworldspawn")){
		}else if(command.equalsIgnoreCase("skull")){
		}else if(command.equalsIgnoreCase("spawn")){
		}else if(command.equalsIgnoreCase("spawnmob")){
			if(args.length == 1){
				List<String> Materiels = (List<String>)YamlConfiguration.loadConfiguration(getResource("materiels.list")).getList("Materials");
				return Materiels;
			}else if(args.length == 2){
				list.add("1");
				return list;
			}
		}else if(command.equalsIgnoreCase("spawnpoint")){
			if(args.length == 2){
				if(sender instanceof Player){
					Player p = (Player)sender;
					Block b = p.getTargetBlock((HashSet<Byte>)null, 7);
					list.add(""+b.getLocation().getX());
					return list;
				}else{
					list.add("0");
				}
			}else if(args.length == 3){
				if(sender instanceof Player){
					Player p = (Player)sender;
					Block b = p.getTargetBlock((HashSet<Byte>)null, 7);
					list.add(""+b.getLocation().getY());
					return list;
				}else{
					list.add("0");
				}
			}else if(args.length == 4){
				if(sender instanceof Player){
					Player p = (Player)sender;
					Block b = p.getTargetBlock((HashSet<Byte>)null, 7);
					list.add(""+b.getLocation().getZ());
					return list;
				}else{
					list.add("0");
				}
			}else if(args.length == 5){
				if(sender instanceof Player){
					Player p = (Player)sender;
					list.add(p.getWorld().getName());
					return list;
				}else{
					for(World w : Bukkit.getWorlds()){
						list.add(w.getName());
					}
					return list;
				}
			}
		}else if(command.equalsIgnoreCase("speed")){
			if(args.length == 1){
				list.add("2");
				return list;
			}
		}else if(command.equalsIgnoreCase("time")){
			if(args.length == 1){
				list.add("set");
				list.add("add");
				list.add("query");
				return list;
			}else if(args.length == 2){
				if(args[0].equalsIgnoreCase("set") | args[0].equalsIgnoreCase("add")){
					list.add("day");
					list.add("night");
					list.add("0");
					return list;
				}
			}
		}else if(command.equalsIgnoreCase("tp")){
		}else if(command.equalsIgnoreCase("unban")){
		}else if(command.equalsIgnoreCase("warp")){
			if(args.length == 1){
				list.add("add");
				list.add("remove");
				list.add("list");
				File[] Files = new File("plugins/EssentialsGreen/Warp").listFiles();
				for(File f : Files){
					list.add(YamlConfiguration.loadConfiguration(f).getString("Name"));
				}
				return list;
			}
		}else if(command.equalsIgnoreCase("whitelist")){
			if(args.length == 1){
				list.add("add");
				list.add("remove");
				list.add("list");
				list.add("on");
				list.add("off");
				list.add("reload");
				return list;
			}
		}else if(command.equalsIgnoreCase("xp")){
			if(args.length == 1){
				list.add("set");
				list.add("add");
				return list;
			}
		}
		return null;
	}
}