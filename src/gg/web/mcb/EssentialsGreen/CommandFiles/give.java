package gg.web.mcb.EssentialsGreen.CommandFiles;

import gg.web.mcb.EssentialsGreen.API.ItemManager;
import gg.web.mcb.EssentialsGreen.API.NumberManager;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class give implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args){
		if(p.hasPermission("EssentialsGreen.give")){
			if(args.length == 0){
				p.sendMessage(EssentialsGreen.prefix + "/give <Player> <Materiel | ID> [Ammount]");
			}else if(args.length == 1){
				p.sendMessage(EssentialsGreen.prefix + "/give " + args[0] + " <ID> [Ammount]");
			}else if(args.length > 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(!(target == null)){
					if(NumberManager.firstNumberCheck(args[1])){
						if(ItemManager.CheckID(new Integer(args[1])) == true){
							if(args.length == 2){
								Material Materiel = ItemManager.getMaterialByID(new Integer(args[1]));
								target.getInventory().addItem(new ItemStack(Materiel, 1));
								p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : 1");
							}else{
								Material Materiel = ItemManager.getMaterialByID(new Integer(args[1]));
								target.getInventory().addItem(new ItemStack(Materiel, new Integer(args[2])));
								p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : " + args[2]);
							}
						}else p.sendMessage(EssentialsGreen.prefix + "ID not Found");
					}else if(ItemManager.CheckMateriel(args[1])){
						if(args.length == 2){
							Material Materiel = Material.matchMaterial(args[1]);
							target.getInventory().addItem(new ItemStack(Materiel, 1));
							p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : 1");
						}else{
							Material Materiel = Material.matchMaterial(args[1]);
							target.getInventory().addItem(new ItemStack(Materiel, new Integer(args[2])));
							p.sendMessage(EssentialsGreen.prefix + "Give " + target.getName() + " " + args[1] + " (" + Materiel + ") Ammount : " + args[2]);
						}
					}else p.sendMessage(EssentialsGreen.prefix + "Materiel not Found");
				}else p.sendMessage(EssentialsGreen.prefix + "This target player is not online");
			}
		}else p.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}