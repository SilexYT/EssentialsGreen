package gg.web.mcb.EssentialsGreen.API;

import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TablistTitleAPI extends JavaAPI {
	public static void sendTabTitle(Player p, String header, String footer) {
		if(header == null) header = "";
		if(footer == null) footer = "";
		CraftPlayer cp = (CraftPlayer) p;
		PlayerConnection connection = cp.getHandle().playerConnection;
		IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
		try{
			Field field = headerPacket.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(headerPacket, tabFoot);
		}catch(Exception e){
        	e.printStackTrace();
        }finally{
        	connection.sendPacket(headerPacket);
        }
    }
}