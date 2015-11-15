/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.Player;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.noip.ccbluex.EssentialsGreen.util.Ban;

/**
 * @author Marco
 *
 */
public interface User {

	public Player getPlayer();
	public OfflinePlayer getOfflinePlayer();
	public UUID getUUID();
	public InetSocketAddress getAddress();
	public void setMute(boolean mute) throws IOException;
	public boolean isMute();
	public Ban getBan();
	public void UpdateUserFile() throws IOException;
}