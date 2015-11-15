package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public interface UpdateManager {

	public void load();
	public void reload();
	public boolean updateNeeded();
	public void downloadlatestfile(String output) throws IllegalStateException, MalformedURLException, ProtocolException, IOException;
}