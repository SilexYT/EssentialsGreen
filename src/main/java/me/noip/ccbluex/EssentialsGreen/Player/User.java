/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.Player;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * @author Marco
 *
 */
public interface User {

	public Player getPlayer();
	@Deprecated
	public OfflinePlayer getOfflinePlayer();
	public UUID getUUID();
	@Deprecated
	public InetSocketAddress getAddress();
	public void setMute(boolean mute) throws IOException;
	public boolean isMute();
	public void setBan(boolean ban, String reason, String author);
	public boolean isBan();
	public void setTempBanWorking(boolean ban, String reason, String author);
	public void UpdateUserFile() throws IOException;
}