package gg.web.mcb.EssentialsGreen.API;

import org.bukkit.entity.Player;

public class ActionBarAPI extends JavaAPI {
	
	//WARNING This API is for the Spigot 1.8.7!!!!
	//The boolean is the finish return. false = error; true = finish;
	public static boolean sendActionBar(Player p, String Message){
		try{
			Class.forName("org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer");
			if(Message == null) Message = "";
			net.minecraft.server.v1_8_R3.PlayerConnection con = ((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) p).getHandle().playerConnection;
			net.minecraft.server.v1_8_R3.IChatBaseComponent chat = net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Message + "\"}");
			net.minecraft.server.v1_8_R3.PacketPlayOutChat packet = new net.minecraft.server.v1_8_R3.PacketPlayOutChat(chat, (byte) 2);
			con.sendPacket(packet);
			return true;
		}catch(ClassNotFoundException e1){}
		return false;
	}
}