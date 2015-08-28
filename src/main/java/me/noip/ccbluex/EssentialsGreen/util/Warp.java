/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.util;

import java.util.UUID;

import org.bukkit.Location;

/**
 * @author Marco
 *
 */
public interface Warp {
	public String getName();
	public Results setName(String Name);
	public UUID getUUID();
	public Location getLocation();
	public Results setLocation(Location loc);
	public Results setLocation(double x, double y, double z, Float yaw, Float pitch, String World);
}