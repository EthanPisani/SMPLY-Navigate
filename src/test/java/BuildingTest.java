import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;


import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {
    private Building building;
    JSONObject userData =  PersistantData.readUserData(PersistantData.getUUID("u"));

    @BeforeEach
    public void setUp() {
        building = new AllumniHallPage(userData) {
        };
    }

    @Test
    public void testBuildingNotNull() {
        assertNotNull(building, "Building object should not be null");
    }
    @Test
    public void testUpdatePOIsOnMap() {
        int originalPOICount = building.poiList.size();
        building.updatePOIsOnMap(1, pointsOfInterest.buildings.MiddlesexCollege);
        int updatedPOICount = building.poiList.size();
        assertEquals(originalPOICount, updatedPOICount, "The number of POIs on the map should not be updated");
    }
    @Test
    public void testSwitchFloors() {
        // Check initial floor
        assertEquals(1, building.currentFloor, "Initial floor should be 1");

        // Change floor and check the updated floor value
        building.changeFloor(2);
        assertEquals(2, building.currentFloor, "After switching, floor should be 2");

        // Change floor again and check the updated floor value
        building.changeFloor(3);
        assertEquals(3, building.currentFloor, "After switching, floor should be 3");

        // Test if the image has changed after switching floors
        Image floor1Image = building.getFloorImagePath(1);
        Image floor2Image = building.getFloorImagePath(2);
        Image floor3Image = building.getFloorImagePath(3);

        assertNotSame(floor1Image, floor2Image, "Floor 1 and Floor 2 images should be different");
        assertNotSame(floor1Image, floor3Image, "Floor 1 and Floor 3 images should be different");
        assertNotSame(floor2Image, floor3Image, "Floor 2 and Floor 3 images should be different");
    }
    @Test
    public void testIsFavourite() {
        pointsOfInterest poi1 = new pointsOfInterest("Room 101", 50, 80, pointsOfInterest.categories.classroom, "101", 1, pointsOfInterest.buildings.MiddlesexCollege);
        pointsOfInterest poi2 = new pointsOfInterest("Stairwell A", 120, 170, pointsOfInterest.categories.stairwell, "A", 1, pointsOfInterest.buildings.MiddlesexCollege);
        building.favList.add(poi1);
        assertTrue(building.isFavourite(poi1), "poi1 should be in the favorites list");
        assertFalse(building.isFavourite(poi2), "poi2 should not be in the favorites list");
    }
}

