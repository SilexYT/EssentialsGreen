package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.IOException;

import me.noip.ccbluex.EssentialsGreen.util.Message;

public interface MessageManager {

	public Message getMessage(String message);
	public void setMessage(String message, String Path) throws IOException;
	public void reload();
	public void save() throws IOException;
	public void create();
	public void createifnotexist();
	public void load();
}