/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.Files;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;
import me.noip.ccbluex.EssentialsGreen.Annotation.Working;

/**
 * @author Marco
 *
 */
public class backup implements CommandExecutor {

	@Working
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender.hasPermission("EssentialsGreen.backup")){
			sender.sendMessage(EssentialsGreen.prefix + "The backup is starting!");
			//Save all data
			{
				//Player saving
				for(Player p : Bukkit.getOnlinePlayers()){
					p.saveData();
				}
				
				Bukkit.savePlayers();
			}
			
			{
				//World saving
				for(World w : Bukkit.getWorlds()){
					w.save();
				}
			}
			//Copy all files
		    Date date = new Date();
		    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy (HH.mm.ss)");
			File[] all = new File("/").listFiles();
			File out = new File("plugins/EssentialsGreen/backups/" + time.format(date));
			out.mkdir();
			for(File f : all){
				try{
					if(f.isHidden() == false | f.exists()){
						Files.copy(f, out);
					}
				}catch(FileNotFoundException e){	
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			sender.sendMessage(EssentialsGreen.prefix + "The Backup is finish or is running!");
		}
		return true;
	}
}