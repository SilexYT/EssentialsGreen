package gg.web.mcb.EssentialsGreen.ApiFiles;

public class NumberManager {
	public static boolean firstNumberCheck(String s){
		String[] Split = s.split("");
		boolean check = false;
		if(Split[1].equalsIgnoreCase("0")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("1")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("2")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("3")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("4")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("5")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("6")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("7")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("8")){
			check = true;
		}else if(Split[1].equalsIgnoreCase("9")){
			check = true;
		}
		return check;
	}
	
	@SuppressWarnings("unused")
	public static boolean IsStringint(String ns){
		if(new Integer(ns) == null){
			return false;
		}else return true;
	}
}