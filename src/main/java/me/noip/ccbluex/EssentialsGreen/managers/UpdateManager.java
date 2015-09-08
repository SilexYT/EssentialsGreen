package me.noip.ccbluex.EssentialsGreen.managers;

import me.noip.ccbluex.EssentialsGreen.util.Results;

public interface UpdateManager {

	public boolean checkUpdate();
	public Results downloadUpdate(String OUTPATH, boolean reload);
	public String getFile();
}