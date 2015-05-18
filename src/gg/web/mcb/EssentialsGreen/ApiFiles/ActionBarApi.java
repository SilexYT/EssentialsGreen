package gg.web.mcb.EssentialsGreen.ApiFiles;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarApi {
	public static void sendActionBar(Player p, String Message){
		if(Message == null) Message = "null";
		Message.replace('&', '§');
		
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"" + Message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
		
		con.sendPacket(packet);
	}
	
	public static void directcancelActionBar(Player p){
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		IChatBaseComponent chat = ChatSerializer.a("{text:}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
		
		con.sendPacket(packet);
	}
}
