/**
 * 
 */
package me.noip.ccbluex.EssentialsGreen.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Marco
 *
 */
public interface FileManager {

	public File getFile(String path);
	public InputStream getResource(String filename, Class<?> Class) throws FileNotFoundException;
	public void copyfile(File in, File out) throws IOException;
	public void copyfile(InputStream in, File out) throws IOException;
	public YamlConfiguration getYaml(String path);
}