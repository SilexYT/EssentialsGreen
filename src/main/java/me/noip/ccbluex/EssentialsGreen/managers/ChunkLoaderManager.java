/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.IOException;
import java.util.Collection;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author Marco
 *
 */
public interface ChunkLoaderManager {

	public Chunk addChunkLoader(Location loc) throws IOException;
	public boolean removeChunkLoader(Location loc);
	public void info(Location loc, Player getsinfo);
	public void reload();
	public void createsave() throws IOException;
	public Collection<Chunk> getChunks();
	public Collection<Block> getChunkLoaders();
	public void loadsave() throws IOException;
	public void save() throws IOException;
	public int start(Plugin plugin);
}