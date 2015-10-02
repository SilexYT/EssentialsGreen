/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.managers;

import org.bukkit.entity.Player;

/**
 * @author Marco
 *
 */
public interface ChatManager {

	public void lock(boolean lock);
	public void send(String message);
	public void clear(Player p);
	public void clear(Player[] p);
	public void islock();
}