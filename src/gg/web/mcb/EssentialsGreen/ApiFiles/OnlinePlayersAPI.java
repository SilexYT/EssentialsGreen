package gg.web.mcb.EssentialsGreen.ApiFiles;

import java.util.ArrayList;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;

public class OnlinePlayersAPI {
	public static ArrayList<String> getAllOnlinePlayers(){
		ArrayList<String> Players = new EssentialsGreen().OnlinePlayers;
		return Players;
	}
}
