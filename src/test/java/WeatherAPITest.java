import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherAPITest {

    @Test
    public void testGetTemperature() {
        String temperature = WeatherAPI.getTemperature();
        assertNotNull(temperature);
        assertFalse(temperature.equals("null"));
        assertTrue(Float.parseFloat(temperature) >= -50 && Float.parseFloat(temperature) <= 50);
    }

}
