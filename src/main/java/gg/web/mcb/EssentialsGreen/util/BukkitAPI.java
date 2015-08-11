package gg.web.mcb.EssentialsGreen.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BukkitAPI {

	public static ArrayList<String> getPlugins(){
		ArrayList<String> Plugins = new ArrayList<>();
		for(Plugin p : Bukkit.getPluginManager().getPlugins()){
			Plugins.add(p.getName());
		}
		return Plugins;
	}
}