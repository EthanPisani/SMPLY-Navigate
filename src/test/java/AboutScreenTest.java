import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AboutScreenTest {

    @Test
    void getFrame() {
        JSONObject userData =  PersistantData.readUserData("admin");
        AboutScreen test = new AboutScreen(userData);
        Assertions.assertNotNull(test.getFrame());
    }
}