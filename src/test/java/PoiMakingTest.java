import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PoiMakingTest {
    private static final String DEFAULT_FILE_PATH = "data/123e4567-e89b-12d3-a456-426655440000.json";
    private static final String USER = "u";
    JSONObject userData =  PersistantData.readUserData(PersistantData.getUUID("u"));

    private PoiMaking poiMaking;

    @BeforeEach
    public void setUp() throws IOException {
        poiMaking = new PoiMaking(userData);
    }

    @Test
    public void testGetCategory() {
        Assertions.assertEquals(
                pointsOfInterest.categories.classroom,
                poiMaking.getCategory("classroom")
        );
    }

    @Test
    public void testGetBuilding() {
        Assertions.assertEquals(
                pointsOfInterest.buildings.MiddlesexCollege,
                poiMaking.getBuilding("MiddlesexCollege")
        );
    }
}
