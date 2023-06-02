/**

 The PoiMaking class represents the process of creating and manipulating a list of points of interest (POIs).
 It contains fields for storing both default and user-created POIs, as well as a list of favorite POIs.
 It also has a JSONObject field for holding user data, and a JSONObject field for holding default POIs.
 The class has a constructor that takes in a JSONObject representing user data, and reads the user's own JSON file,
 loading the created POIs into the POI list, and the favorited POIs into the favorites list.
 The class also has methods for converting string values to corresponding category and building types,
 and for getting and setting the user's POIs and favorite list. Additionally, it has a method for saving user data.
 The class depends on the pointsOfInterest class, which represents a single point of interest, with properties such as
 name, x and y coordinates, category, building, and floor.
 The class uses the org.json library for parsing and manipulating JSON objects and arrays, and the java.nio library
 for file input and output.
 */
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * A class of tools used to convert from Arraylists to JSON and Backwards
 */
public class PoiMaking {
    private ArrayList<pointsOfInterest> userPOIs; // stores both default and user created POIS
    private ArrayList<pointsOfInterest> favoriteList;
    private JSONObject jsonObject;
    private JSONObject defaultPOIs;

    /**
     * Gets the JSON object.
     *
     * @return the JSON object.
     */
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    /**
     * Gets the default POIs.
     *
     * @return the default POIs.
     */
    public JSONObject getDefaultPOIs() {
        return defaultPOIs;
    }

    /**
     * Constructs a PoiMaking object, which reads the user's own JSON file, loading the created POIs into the POI list,
     * and the favorited POIs into the favorites list.
     *
     * @param userData the JSON object representing user data.
     * @throws IOException if an error occurs while reading the JSON file.
     */
    public PoiMaking(JSONObject userData) throws IOException {//send into filePath the path to go to the individual json file

        this.userPOIs = new ArrayList<>();
        this.favoriteList = new ArrayList<>();
        //******** OPENS THE USER'S OWN JSON FILE AND LOADS THE CREATED POI'S INTO THE POI LIST
        // AND LOADS THEIR FAVORITED POIS INTO THE FAVORITES LIST
        this.jsonObject = userData;

        JSONArray poiList = jsonObject.getJSONArray("pointsOfInterest");
        for (int i = 0; i < poiList.length(); i++) {
            JSONObject userJson = poiList.getJSONObject(i);
            String name = userJson.getString("name");

            pointsOfInterest.categories categ = getCategory(userJson.getString("category"));

            pointsOfInterest.buildings building = getBuilding(userJson.getString("building"));

            int floor = userJson.getInt("floor");

            String roomNum = userJson.getString("roomNumber");

            int xCoord = userJson.getInt("xCoord");

            int yCoord = userJson.getInt("yCoord");

            boolean isDefault = userJson.getBoolean("isDefault");
            pointsOfInterest POI = new pointsOfInterest(name, xCoord, yCoord, categ, roomNum, floor, building);
            POI.setDefault(isDefault);
            this.userPOIs.add(POI);

        }

        JSONArray favorite = jsonObject.getJSONArray("favorites");
        for (int i = 0; i < favorite.length(); i++) {
            JSONObject userJson = favorite.getJSONObject(i);
            String name = userJson.getString("name");

            pointsOfInterest.categories categ = getCategory(userJson.getString("category"));

            pointsOfInterest.buildings building = getBuilding(userJson.getString("building"));

            int floor = userJson.getInt("floor");

            String roomNum = userJson.getString("roomNumber");

            int xCoord = userJson.getInt("xCoord");

            int yCoord = userJson.getInt("yCoord");

            boolean isDefault = userJson.getBoolean("isDefault");

            pointsOfInterest POI = null;
            for (int j = 0; j < this.userPOIs.size(); j++) {
                pointsOfInterest poi = this.userPOIs.get(j);
                if (poi.getName().equals(name) && categ == poi.getCategory() && building == poi.getBuilding()
                        && floor == poi.getFloor() && roomNum.equals(poi.getRoomNumber()) && xCoord == poi.getXCoord()
                        && yCoord == poi.getYCoord()) {
                    POI = poi;
                }
            }
            if (POI == null) {
                throw new RuntimeException();
                //throws except
            }
            this.favoriteList.add(POI);
        }
    }

    /**

     Retrieves the category of a POI given its name.
     @param cate The name of the category to retrieve.
     @return The category of the POI.
     */
    public pointsOfInterest.categories getCategory(String cate) {
        switch (cate) {
            case "classroom":
                return pointsOfInterest.categories.classroom;
            case "stairwell":
                return pointsOfInterest.categories.stairwell;
            case "elevator":
                return pointsOfInterest.categories.elevator;
            case "washroom":
                return pointsOfInterest.categories.washroom;
            case "entrance":
                return pointsOfInterest.categories.entrance;
            case "exit":
                return pointsOfInterest.categories.exit;
            case "genLab":
                return pointsOfInterest.categories.genLab;
            case "restaurant":
                return pointsOfInterest.categories.restaurant;
            case "CsLab":
                return pointsOfInterest.categories.CsLab;
            case "CsCollaborativeRoom":
                return pointsOfInterest.categories.CsCollaborativeRoom;
            case "userPOI":
                return pointsOfInterest.categories.userPOI;
        }
        return null;
    }
    /**

     Retrieves the building of a POI given its name.
     @param build The name of the building to retrieve.
     @return The building of the POI.
     */
    public pointsOfInterest.buildings getBuilding(String build) {
        switch (build) {
            case "MiddlesexCollege":
                return pointsOfInterest.buildings.MiddlesexCollege;
            case "AlumniHall":
                return pointsOfInterest.buildings.AlumniHall;
            case "AdvancedFacilityForAvianResearch":
                return pointsOfInterest.buildings.AdvancedFacilityForAvianResearch;
        }
        return null;
    }
    /**

     Retrieves the list of user-created POIs.
     @return The list of user-created POIs.
     */
    public ArrayList<pointsOfInterest> getUserPOIs() {
        return this.userPOIs;
    }
    /**

     Retrieves the list of favorited POIs.
     @return The list of favorited POIs.
     */
    public ArrayList<pointsOfInterest> getFavoriteList() {
        return this.favoriteList;
    }
    /**

     Saves the POI data and user data to a JSON file.
     @param poiList The list of POIs to save.
     @param favList The list of favorite POIs to save.
     */
    public void saveData(ArrayList<pointsOfInterest> poiList, ArrayList<pointsOfInterest> favList) {
        JSONObject tempObj = new JSONObject();
        tempObj.put("username", this.jsonObject.getString("username"));
        tempObj.put("password", this.jsonObject.getString("password"));
        tempObj.put("uuid", this.jsonObject.getString("uuid"));
        tempObj.put("isAdmin", this.jsonObject.getBoolean("isAdmin"));
        JSONArray tempArrayPOI = new JSONArray();
        for (int i = 0; i < poiList.size(); i++) {
            JSONObject currPOI = new JSONObject();
            pointsOfInterest poi = poiList.get(i);
            currPOI.put("name", poi.getName());
            currPOI.put("description", poi.getDescription());
            currPOI.put("category", poi.getCategory());
            currPOI.put("floor", poi.getFloor());
            currPOI.put("building", poi.getBuilding());
            currPOI.put("roomNumber", poi.getRoomNumber());
            currPOI.put("xCoord", poi.getXCoord());
            currPOI.put("yCoord", poi.getYCoord());
            currPOI.put("isDefault", poi.isDefault());
            tempArrayPOI.put(currPOI);
        }
        JSONArray tempArrayFav = new JSONArray();
        for (int i = 0; i < favList.size(); i++) {
            JSONObject currPOI = new JSONObject();
            pointsOfInterest poi = favList.get(i);
            currPOI.put("name", poi.getName());
            currPOI.put("description", poi.getDescription());
            currPOI.put("category", poi.getCategory());
            currPOI.put("floor", poi.getFloor());
            currPOI.put("building", poi.getBuilding());
            currPOI.put("roomNumber", poi.getRoomNumber());
            currPOI.put("xCoord", poi.getXCoord());
            currPOI.put("yCoord", poi.getYCoord());
            currPOI.put("isDefault", poi.isDefault());
            tempArrayFav.put(currPOI);
        }
        tempObj.put("pointsOfInterest", tempArrayPOI);
        tempObj.put("favorites", tempArrayFav);

        PersistantData p = new PersistantData();
        p.saveUserData(tempObj);
    }
}