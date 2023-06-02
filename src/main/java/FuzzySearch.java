import org.apache.commons.text.similarity.FuzzyScore;

import java.util.*;
/**

 The FuzzySearch class provides methods for performing fuzzy search on a list of points of interest (POI) based on a query string.

 It uses the FuzzyScore library to compute fuzzy match scores for each POI name and returns a list of POIs that match the query string

 with a minimum score threshold.
 @author Ethan
 */
public class FuzzySearch {

    /**

     Searches for points of interest (POI) that match the given query string.

     @param query The query string to search for.

     @param poiList The list of points of interest to search within.

     @return A list of points of interest that match the query string with a fuzzy match score above the minimum threshold.
     */
    public static List<pointsOfInterest> searchPOI(String query, List<pointsOfInterest> poiList) {
        List<pointsOfInterest> matchedPOI = new ArrayList<>();
        FuzzyScore fuzzyScore = new FuzzyScore(Locale.ENGLISH);

        for (pointsOfInterest poi : poiList) {
            int score = fuzzyScore.fuzzyScore(query, poi.getName());
            if (score > 0) { // set minimum score to 50 (you can adjust this)
                poi.setFuzzyMatchScore(score);
                matchedPOI.add(poi);
            }
        }

        Collections.sort(matchedPOI, new Comparator<pointsOfInterest>() {
            @Override
            public int compare(pointsOfInterest poi1, pointsOfInterest poi2) {
                return Integer.compare(poi2.getFuzzyMatchScore(), poi1.getFuzzyMatchScore());
            }
        });

        return matchedPOI;
    }

}
