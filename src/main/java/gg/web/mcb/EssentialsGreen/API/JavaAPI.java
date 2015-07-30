package gg.web.mcb.EssentialsGreen.API;

import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class JavaAPI {
	Collection<Object> APIs = new ArrayList<Object>();
	
	public static boolean RegisterAPIs(Collection<Object> APIs){
		JavaAPI API = new JavaAPI();
		API.APIs = APIs;
		return true;
	}

	public Collection<Object> getAPIs(){
		return APIs;
	}

	public void addAPI(Object API){
		APIs.add(API);
	}

	public void removeAPI(Object API){
		APIs.remove(API);
	}
	
	public static ArrayList<String> getBukkitPlugins(){
		Plugin[] pls = Bukkit.getPluginManager().getPlugins();
		ArrayList<String> plarray = new ArrayList<String>();
		for(int i = 0; i < pls.length; i++){
			plarray.add(pls[i].getName());
		}
		return plarray;
	}
}