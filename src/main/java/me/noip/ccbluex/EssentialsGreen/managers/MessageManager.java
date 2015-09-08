package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MessageManager {

	public String getMessage(String message);
	public void setMessage(String message, String Path) throws IOException;
	public void reload();
	public void load() throws FileNotFoundException, IOException;
	public void save() throws IOException;
}