package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class clear implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender.hasPermission("EssentialsGreen.clear")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player p = (Player)sender;
					ItemStack AIR = new ItemStack(Material.AIR);
					p.getInventory().clear();
					p.getInventory().setHelmet(AIR);
					p.getInventory().setChestplate(AIR);
					p.getInventory().setLeggings(AIR);
					p.getInventory().setBoots(AIR);
					p.sendMessage(EssentialsGreen.prefix + "Inventory cleared!");
				}else sender.sendMessage(EssentialsGreen.prefix + "/clear <Player>");
			}else if(args.length > 0){
				Player Target = Bukkit.getPlayer(args[0]);
				if(!(Target == null)){
					ItemStack AIR = new ItemStack(Material.AIR);
					Target.getInventory().clear();
					Target.getInventory().setHelmet(AIR);
					Target.getInventory().setChestplate(AIR);
					Target.getInventory().setLeggings(AIR);
					Target.getInventory().setBoots(AIR);
					Target.sendMessage(EssentialsGreen.prefix + "Inventory cleared!");
					sender.sendMessage(EssentialsGreen.prefix + "Inventory cleared from " + args[0] + "!");
				}else sender.sendMessage(EssentialsGreen.prefix + "This Player is not online!");
			}
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}