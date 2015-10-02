package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.FileNotFoundException;
import java.io.IOException;

import me.noip.ccbluex.EssentialsGreen.util.Message;

public interface MessageManager {

	public Message getMessage(String message);
	public void setMessage(String message, String Path) throws IOException;
	public void reload();
	public void load() throws FileNotFoundException, IOException;
	public void save() throws IOException;
}