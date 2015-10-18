/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.IOException;
import java.util.Collection;

import org.bukkit.entity.Player;

/**
 * @author Marco
 *
 */
public interface ChatManager {

	public boolean send(Player p, String message);
	public boolean send(Collection<Player> p, String message);
	public boolean clear(Player p);
	public boolean clear(Collection<Player> p);
	public void mute(Player p, boolean mute) throws IOException;
	public void mute(Collection<Player> p, boolean mute) throws IOException;
}