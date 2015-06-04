package gg.web.mcb.EssentialsGreen.API;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarAPI{
	public static void sendActionBar(Player p, String Message){
		if(Message == null) Message = "null";
		Message.replace('&', '§');
		
		PlayerConnection con = ((CraftPlayer) p).getHandle().playerConnection;
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
