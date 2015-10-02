/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.Listeners;

import java.io.IOException;
import java.util.Collection;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

/**
 * @author Marco
 *
 */
public class ChunkLoaderListener implements Listener {

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Collection<Block> block = EssentialsGreen.getEssentialsGreenManager().getChunkLoaderManager().getChunkLoaders();
			if(block.contains(e.getClickedBlock())){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void BlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		Collection<Block> block = EssentialsGreen.getEssentialsGreenManager().getChunkLoaderManager().getChunkLoaders();
		if(block.contains(e.getBlock())){
			if(e.isCancelled()){
				try{
					EssentialsGreen.getEssentialsGreenManager().getChunkLoaderManager().removeChunkLoader(e.getBlock().getLocation());
					p.sendMessage(EssentialsGreen.prefix + EssentialsGreen.getEssentialsGreenManager().getMessageManager().getMessage("youhasthebreakthechunkloader"));
				}catch (IOException e1) {
					e.setCancelled(true);
					e1.printStackTrace();
				}
			}
		}
	}
}