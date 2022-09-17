import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/aggregated/3094802");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int errorCode = httpURLConnection.getResponseCode();
            switch (errorCode) {
                case 200:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        content.append(inputLine);
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    Gson gson = new Gson();
                    KrakowWeather krakowWeather = gson.fromJson(content.toString(), KrakowWeather.class);
                    File file = new File("WeatherKrakow.txt");
                    PrintWriter writer = new PrintWriter(file);
                    writer.println(krakowWeather);
                    writer.close();
                    if (file.exists()) {
                        System.out.println("Data has been saved to " + file.getAbsolutePath() + " file");
                    }
                    break;
                case 400:
                    System.out.println("HTTP Status-Code 400: Bad Request.");
                    break;
                case 403:
                    System.out.println("HTTP Status-Code 403: Forbidden.");
                    break;
                case 404:
                    System.out.println("HTTP Status-Code 404: Not Found.");
                    break;
                case 405:
                    System.out.println("HTTP Status-Code 405: Method Not Allowed.");
                    break;
                case 408:
                    System.out.println("HTTP Status-Code 408: Request Time-Out.");
                    break;
                case 409:
                    System.out.println("HTTP Status-Code 409: Conflict.");
                    break;
                case 500:
                    System.out.println("HTTP Status-Code 500: Internal Server Error.");
                    break;
                case 501:
                    System.out.println("HTTP Status-Code 501: Not Implemented.");
                    break;
                case 503:
                    System.out.println("HTTP Status-Code 503: Service Unavailable.");
                    break;
                case 505:
                    System.out.println("HTTP Status-Code 505: HTTP Version Not Supported.");
                    break;
                default:
                    System.out.println("No internet connection!");
            }
        }
        catch (IOException e) {
            System.out.println("No internet connection!");
        }
    }
}
