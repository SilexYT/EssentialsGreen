package gg.web.mcb.EssentialsGreen.util;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.Location;

public interface WarpManager {
	public Collection<Warp> getWarps();
	public Warp getWarp(String Warp);
	public Warp getWarp(UUID uuid);
	public Results addWarp(String Name, Location loc);
	public Results addWarp(String Name, double x, double y, double z, Float yaw, Float pitch, String World);
	public Results removeWarp(String Name);
	public boolean contains(String Name);
}