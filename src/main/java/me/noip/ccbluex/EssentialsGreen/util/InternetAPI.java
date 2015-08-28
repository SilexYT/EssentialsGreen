package me.noip.ccbluex.EssentialsGreen.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class InternetAPI {
	public static void downloadFile(String url_str, String OutputPath) throws IllegalStateException, MalformedURLException, ProtocolException, IOException {
		FileOutputStream os = new FileOutputStream(OutputPath);
		URL url = new URL(url_str.replace(" ", "%20"));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();
		if(responseCode == HttpURLConnection.HTTP_OK){
			byte tmp_buffer[] = new byte[4096];
			InputStream is = conn.getInputStream();
			int n;
			while((n = is.read(tmp_buffer)) > 0){
				os.write(tmp_buffer, 0, n);
				os.flush();
			}
			os.close();
		}else{
			os.close();
			throw new IllegalStateException("HTTP response: " + responseCode);
		}
	}

	public static String ReadURL(String URL){
		String re = "";
    	try{
    		URL url = new URL(URL);
    		Reader is = new InputStreamReader(url.openStream());
    		BufferedReader in = new BufferedReader(is);
    		for(String s; (s = in.readLine()) != null;){
    			re = re + " " +s;
    		}
    		in.close();
    	}catch(MalformedURLException e){
    		System.out.println("MalformedURLException: " + e);
    	}catch(IOException e){
    		System.out.println("IOException: " + e);
    	}
    	return re;
    }
}