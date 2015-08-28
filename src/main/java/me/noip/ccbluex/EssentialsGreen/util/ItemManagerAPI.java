package me.noip.ccbluex.EssentialsGreen.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManagerAPI {
	public static Boolean CheckMateriel(String Materiel){
		boolean obj;
		if(Material.matchMaterial(Materiel) == null){
			obj = false;
		}else{
			obj = true;
		}
		return obj;
	}
	
	@SuppressWarnings("deprecation")
	public static Boolean CheckID(int ID){
		boolean obj;
		if(Material.getMaterial(ID) == null){
			obj = false;
		}else{
			obj = true;
		}
		return obj;
	}
	
	@SuppressWarnings("deprecation")
	public static Material getMaterialByID(int ID){;
		Material M = Material.getMaterial(ID);
		return M;
	}
	
	@SuppressWarnings("deprecation")
	public static int getIDByMateriel(Material Materiel){
		ItemStack Item = new ItemStack(Materiel);
		int ID = Item.getTypeId();
		return ID;
	}
}