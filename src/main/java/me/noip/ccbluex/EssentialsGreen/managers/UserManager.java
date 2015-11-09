package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.noip.ccbluex.EssentialsGreen.Player.User;

public interface UserManager {
	public User getUser(UUID uuid);
	public User getUser(String name);
	public User createUser(Player p, boolean isNew) throws IOException;
	public boolean existUser(String name);
	public boolean existUser(UUID uuid);
}