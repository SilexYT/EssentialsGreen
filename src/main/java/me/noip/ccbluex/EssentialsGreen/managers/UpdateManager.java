package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import me.noip.ccbluex.EssentialsGreen.EssentialsGreen;

public interface UpdateManager {

	public boolean updateNeeded() throws IOException, SAXException, ParserConfigurationException;
	public void REGISTERXML() throws IOException, SAXException, ParserConfigurationException;
	public Double getVersion();
	public String getLink();
	public void downloadUpdate(String output, String url, EssentialsGreen eg);
}