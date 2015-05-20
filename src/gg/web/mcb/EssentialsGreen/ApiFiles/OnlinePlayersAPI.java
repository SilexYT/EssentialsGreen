package gg.web.mcb.EssentialsGreen.ApiFiles;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;

public class OnlinePlayersAPI {
	public static ArrayList<String> getAllOnlinePlayers(){
		ArrayList<String> Players = new EssentialsGreen().OnlinePlayers;
		return Players;
	}
	
	public static boolean IfPlayerOnline(String Name){
		if(!(Bukkit.getPlayer(Name) == null)){
			return true;
		}else return false;
	}
}