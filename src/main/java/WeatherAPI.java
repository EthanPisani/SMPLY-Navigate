
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
/**
 * The WeatherAPI class provides a method to retrieve the current temperature in Celsius from an external weather API.
 * @author Ethan
 */
public class WeatherAPI {
    /**

     This method retrieves the current temperature in Celsius from an external weather API.

     @return A string representation of the current temperature in Celsius with one decimal place, or "null" if an error occurs.
     */
    public static String getTemperature() {
        try {
            // Open a connection to the API
            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=42.98&longitude=-81.23&hourly=temperature_2m&current_weather=true&forecast_days=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            // Read the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject curWeather = jsonObject.getJSONObject("current_weather");
            float temperature = curWeather.getFloat("temperature");
            String temp =  String.format("%.1f", temperature);// Format temperature with one decimal place

            // Print the temperature to the console and return as a string
            System.out.println("Temperature: " + temperature + " °C");
            return temp;
        } catch (Exception e) {

            return "null";
        }
    }
}

