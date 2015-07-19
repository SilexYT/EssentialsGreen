package gg.web.mcb.EssentialsGreen.API;

public class StringAPI implements JavaAPI {

	public static int getCount(String input, char c) {
	    int count = 0;
	    for (char act : input.toCharArray()) {
	        if (act == c) {
	            count++;
	        }
	    }
	    return count;
	}

	@Override
	public boolean isAPI() {
		return true;
	}
}