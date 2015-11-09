/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.util;

/**
 * @author Marco
 *
 */
public enum BanType {

	Perma,Temp;
	
	public static BanType of(String bantype){
		if(bantype.equalsIgnoreCase("perma")){
			return Perma;
		}else if(bantype.equalsIgnoreCase("temp")){
			return Temp;
		}
		return null;
	}
}