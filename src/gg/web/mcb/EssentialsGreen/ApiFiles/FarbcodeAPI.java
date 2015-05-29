package gg.web.mcb.EssentialsGreen.ApiFiles;

public class FarbcodeAPI {
	public static String RemoveFarbcodes(String Text){
		Text.replace("§0", "");
		Text.replace("§1", "");
		Text.replace("§2", "");
		Text.replace("§3", "");
		Text.replace("§4", "");
		Text.replace("§5", "");
		Text.replace("§6", "");
		Text.replace("§7", "");
		Text.replace("§8", "");
		Text.replace("§9", "");
		Text.replace("§a", "");
		Text.replace("§b", "");
		Text.replace("§c", "");
		Text.replace("§d", "");
		Text.replace("§e", "");
		Text.replace("§f", "");
		return Text;
	}
}
