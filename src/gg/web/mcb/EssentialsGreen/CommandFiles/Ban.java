package gg.web.mcb.EssentialsGreen.CommandFiles;

import java.io.File;
import java.io.IOException;
import gg.web.mcb.EssentialsGreen.MainPackage.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Ban implements CommandExecutor {
	
	main plugin;
	
	public Ban(main main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String Label, String[] args) {
		if(p.hasPermission("EssentialsGreen.ban")){
			if(args.length == 0){
				p.sendMessage(main.prefix + "/ban <Player> <Reason>");
			}else if(args.length == 1){
				p.sendMessage(main.prefix + "/ban " + args[0] + " <Reason>");
			}else if(args.length > 1){
				String Reason = "";
				File UserFile = new File("plugins/EssentialsGreen/UserData/" + args[0] + ".data");
				YamlConfiguration UserFileYaml = YamlConfiguration.loadConfiguration(UserFile);
				
				for(int i = 1; args.length > i; i++){
					Reason = Reason + " " + args[i];
				}
				
				if(UserFile.exists()){
					if(!PermissionsEx.getUser(args[0]).has("EssentialsGreen.ban.exempt") | Bukkit.getOperators().contains(args[0])){
						UserFileYaml.set("Ban.Enable", "true");
						UserFileYaml.set("Ban.Reason", Reason);
						Player target = Bukkit.getPlayer(args[0]);
						if(!(target == null)){
							target.kickPlayer(plugin.getConfig().getString("Ban-Prefix").replace('&', '§') + Reason);
						}
						try{UserFileYaml.save(UserFile);}catch (IOException e){}
						p.sendMessage(main.prefix + args[0] + " banned");
					}else p.sendMessage(main.prefix + "You can not capture the player as he permmision the EssentialsGreen.ban.exempt estate");
				}else p.sendMessage(main.prefix + "This player has never been on the server");
			}
		}else p.sendMessage(main.prefix + "You do not have the required permissions");
		return true;
	}
}
