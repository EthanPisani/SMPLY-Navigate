import org.apache.commons.text.similarity.FuzzyScore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FuzzySearchTest {

    @Test
    public void testSearchPOI() {
        List<pointsOfInterest> poiList = new ArrayList<>();
        poiList.add(new pointsOfInterest("Library", 1, 1, pointsOfInterest.categories.classroom,
                "212", 2, pointsOfInterest.buildings.MiddlesexCollege));
        poiList.add(new pointsOfInterest("Gym", 2, 2, pointsOfInterest.categories.classroom,
                "101", 1, pointsOfInterest.buildings.AlumniHall));

        String query = "Libry";
        List<pointsOfInterest> matchedPOI = FuzzySearch.searchPOI(query, poiList);

        assertEquals(1, matchedPOI.size());
        assertEquals("Library", matchedPOI.get(0).getName());
    }
}
