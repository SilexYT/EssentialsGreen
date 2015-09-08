/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.managers;

/**
 * @author Marco
 *
 */
public interface EssentialsGreenManager {
	public String getName();
	public String getVersion();
	public WarpManager getWarpManager();
	public void ReloadGroupPrefix();
	public UpdateManager getUpdateManager();
	public MessageManager getMessageManager();
}