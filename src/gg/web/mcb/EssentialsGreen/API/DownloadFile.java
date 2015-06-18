package gg.web.mcb.EssentialsGreen.API;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DownloadFile{
	@SuppressWarnings("resource")
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
            while ((n = is.read(tmp_buffer)) > 0) {
                os.write(tmp_buffer, 0, n);
                os.flush();
            }
        }else{
            throw new IllegalStateException("HTTP response: " + responseCode);
        }
    }
}
