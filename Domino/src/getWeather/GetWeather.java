package getWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWeather {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://weather.yahoo.co.jp/weather/jp/14/4610.html");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStream in = httpConn.getInputStream();
		BufferedReader r = new BufferedReader(new InputStreamReader(in, "UTF8"));
		for (;;) {
			String line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println("\r\n" + line);
		}
	}
}
