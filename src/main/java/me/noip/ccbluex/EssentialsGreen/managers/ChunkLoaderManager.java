/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * @author Marco
 *
 */
public interface ChunkLoaderManager {

	public Chunk addChunkLoader(Location loc) throws IOException;
	public boolean removeChunkLoader(Location loc) throws IOException;
	public void info(Location loc, Player getsinfo);
	public void reload();
	public Collection<Chunk> getChunks();
	public Collection<Block> getChunkLoaders();
	public void start() throws FileNotFoundException, IOException;
}