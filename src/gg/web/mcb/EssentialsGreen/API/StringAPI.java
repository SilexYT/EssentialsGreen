package gg.web.mcb.EssentialsGreen.API;

public class StringAPI extends JavaAPI {

	public static int getCount(String input, char c) {
	    int count = 0;
	    for (char act : input.toCharArray()) {
	        if (act == c) {
	            count++;
	        }
	    }
	    return count;
	}
}