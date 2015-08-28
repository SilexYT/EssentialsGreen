package me.noip.ccbluex.EssentialsGreen.util;

public class StringAPI {

	public static int getCount(String input, char c) {
	    int count = 0;
	    for (char act : input.toCharArray()) {
	        if (act == c) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public static String toCompleteString(String[] args, int start){
		String Text = "";
		for(int i = start; args.length > i; i++){
			if(Text != ""){
				Text = Text + " " + args[i];
			}else{
				Text = args[i];
			}
		}
		return Text;
	}
	
	public static boolean isNumber(String a){
	   boolean isNumber;
	   try{
	      Long.parseLong(a);
	      isNumber = true;
	   }catch(NumberFormatException e){
	      isNumber = false;
	   }
	   return isNumber;
	}
}