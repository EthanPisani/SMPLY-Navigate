import org.junit.Assert;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class pointsOfInterestTest {
    pointsOfInterest poi = new pointsOfInterest("Test POI", 20, 30,
            pointsOfInterest.categories.classroom, "123", 1, pointsOfInterest.buildings.MiddlesexCollege);
    @BeforeEach
    public void setup(){
        poi.setName("Test POI");
        poi.setyCoord(30);
        poi.setxCoord(20);
        poi.setCategory(pointsOfInterest.categories.classroom);
        poi.setDefault(true);
        poi.setFuzzyMatchScore(34);
    }

    @Test
    void setxCoord() {
        poi.setxCoord(15);
        Assertions.assertEquals(15, poi.getXCoord());
    }

    @Test
    void setyCoord() {
        poi.setyCoord(132);
        Assertions.assertEquals(132, poi.getYCoord());
    }

    @Test
    void isDefault() {
        Assertions.assertNotNull(poi.isDefault());
    }

    @Test
    void setDefault() {
        poi.setDefault(true);
        Assertions.assertEquals(true, poi.isDefault());
        poi.setDefault(false);
        Assertions.assertEquals(false, poi.isDefault());
    }

    @Test
    void getDescription() {
        Assertions.assertNotNull(poi.getDescription());
    }

    @Test
    void getCategory() {
        Assertions.assertNotNull(poi.getCategory());
    }

    @Test
    void getFloor() {
        Assertions.assertEquals(1, poi.getFloor());
    }

    @Test
    void getBuilding() {
        Assertions.assertEquals(pointsOfInterest.buildings.MiddlesexCollege, poi.getBuilding());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Test POI", poi.getName());
    }

    @Test
    void getRoomNumber() {
        Assertions.assertEquals("123", poi.getRoomNumber());
    }

    @Test
    void getXCoord() {
        Assertions.assertEquals(20, poi.getXCoord());
    }

    @Test
    void getYCoord() {
        Assertions.assertEquals(30, poi.getYCoord());
    }

    @Test
    void getPath() {
        Assertions.assertEquals("imgs/POIs/classroom.png", poi.getPath());
    }

    @Test
    void setName() {
        poi.setName("Potatoe");
        Assertions.assertEquals("Potatoe", poi.getName());
    }

    @Test
    void setDescription() {
        poi.setDescription("Potatoe");
        Assertions.assertEquals("Potatoe", poi.getDescription());
    }

    @Test
    void setCategory() {
        poi.setCategory(pointsOfInterest.categories.elevator);
        Assertions.assertEquals(pointsOfInterest.categories.elevator, poi.getCategory());
    }

    @Test
    void setFuzzyMatchScore() {
        poi.setFuzzyMatchScore(12);
        Assertions.assertEquals(12, poi.getFuzzyMatchScore());
    }

    @Test
    void getFuzzyMatchScore() {
        Assertions.assertEquals(34, poi.getFuzzyMatchScore());
    }
}
