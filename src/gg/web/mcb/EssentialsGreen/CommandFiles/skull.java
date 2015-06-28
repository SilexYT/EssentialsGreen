package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.EssentialsGreen;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class skull implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,String[] args) {
		if(sender.hasPermission("EssentialsGreen.skull")){
			if(sender instanceof Player){
				Player p  = (Player)sender;
				if(args.length == 0){
					ItemStack Item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
					SkullMeta Meta = (SkullMeta)Item.getItemMeta();
					Meta.setOwner(p.getName());
					Item.setItemMeta(Meta);
					p.getInventory().addItem(Item);
					p.sendMessage(EssentialsGreen.prefix + "Skull add...");
				}else if(args.length > 0){
					ItemStack Item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
					SkullMeta Meta = (SkullMeta)Item.getItemMeta();
					Meta.setOwner(args[0]);
					Item.setItemMeta(Meta);
					p.getInventory().addItem(Item);
					p.sendMessage(EssentialsGreen.prefix + "Skull add...");
				}
			}else sender.sendMessage(EssentialsGreen.prefix + "You must be a Player!");
		}else sender.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}