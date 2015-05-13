package gg.web.mcb.EssentialsGreen.CommandFiles;

import java.io.File;
import java.io.IOException;
import gg.web.mcb.EssentialsGreen.MainPackage.EssentialsGreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class Unban implements CommandExecutor {
	
	EssentialsGreen plugin;
	
	public Unban(EssentialsGreen main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args) {
		if(p.hasPermission("EssentialsGreen.unban")){
			if(args.length == 0){
				p.sendMessage(EssentialsGreen.prefix + "/unban <Player>");
			}else if(args.length == 1){
				File UserFile = new File("plugins/EssentialsGreen/UserData/" + args[0] + ".data");
				YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
				if(UserFile.exists()){
					UserFileYaml.set("Ban.Enable", "false");
					UserFileYaml.set("Ban.Reason", "null");
					try{UserFileYaml.save(UserFile);}catch (IOException e){}
					p.sendMessage(EssentialsGreen.prefix + args[0] + " is not more Banned!");
				}else p.sendMessage(EssentialsGreen.prefix + "This player has never been on the server");
			}else p.sendMessage(EssentialsGreen.prefix + "/unban <Player>");
		}else p.sendMessage(EssentialsGreen.prefix + "You do not have the required permissions");
		return true;
	}
}