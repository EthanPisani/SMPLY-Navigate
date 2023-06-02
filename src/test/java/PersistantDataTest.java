import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PersistantDataTest {

    private final PersistantData persistantData = new PersistantData();

    @Test
    void saveUserDataTest() throws IOException {
        JSONObject userData = new JSONObject();
        userData.put("uuid", "test-uuid");
        userData.put("username", "John Doe");

        persistantData.saveUserData(userData);

        File file = new File("data/test-uuid.json");
        Assertions.assertTrue(file.exists());

        file.delete();
    }

    @Test
    void validUserTest() {
        boolean hasUser = PersistantData.validUser("test-uuid");
        Assertions.assertFalse(hasUser);
    }

    @Test
    void readUserDataTest() {
        JSONObject userData = PersistantData.readUserData("test-uuid");
        Assertions.assertNull(userData);
    }

    @Test
    void convertToArrayListTest() {
        JSONObject pointsOfInterest = new JSONObject();
        JSONArray poiArray = new JSONArray();
        JSONObject poi1 = new JSONObject();
        poi1.put("name", "POI 1");
        poi1.put("lat", 40.7128);
        poi1.put("lng", -74.0060);
        poiArray.put(poi1);
        pointsOfInterest.put("pointsOfInterest", poiArray);

        ArrayList<JSONObject> poiList = PersistantData.convertToArrayList(pointsOfInterest);
        Assertions.assertEquals(1, poiList.size());
        JSONObject result = poiList.get(0);
        Assertions.assertEquals("POI 1", result.getString("name"));
        Assertions.assertEquals(40.7128, result.getDouble("lat"));
        Assertions.assertEquals(-74.0060, result.getDouble("lng"));
    }

    @Test
    void getUUIDTest() {
        String uuid = PersistantData.getUUID("John Doe");
        Assertions.assertEquals("test-uuid", uuid);
    }
}
