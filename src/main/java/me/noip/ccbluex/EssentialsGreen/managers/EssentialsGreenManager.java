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
	public UserManager getUserManager();
	public WarpManager getWarpManager();
	public void ReloadGroupPrefix();
	public UpdateManager getUpdateManager();
	public MessageManager getMessageManager();
	public ChunkLoaderManager getChunkLoaderManager();
	public FileManager getFileManager();
	public ChatManager getChatManager();
}