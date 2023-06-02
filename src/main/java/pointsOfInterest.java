/**

 The pointsOfInterest class represents a point of interest (POI) in a map or location-based application.

 It stores information such as the name, description, category, room number, coordinates, floor, building,

 and favorite status of the POI. It also provides methods to get and set the various properties of the POI.
 @author Daniel, Shuja, Leo
 */
public class pointsOfInterest {

    /**
     * POI categories
     */
    public enum categories {
        classroom, stairwell, elevator, washroom, entrance, exit, genLab, restaurant, CsLab, CsCollaborativeRoom, userPOI
    }

    /**
     * Building Types
     */
    public enum buildings{
        MiddlesexCollege, AlumniHall, AdvancedFacilityForAvianResearch
    }
    private String name;
    private String description;
    private categories category;
    private String roomNumber;

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    private int xCoord;
    private int yCoord;
    private int floor;
    private buildings building;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    private boolean isDefault = true;
    private int matchScore;

    /**

     Constructor for creating a pointsOfInterest object.
     @param name The name of the POI.
     @param xCoord The x-coordinate of the POI.
     @param yCoord The y-coordinate of the POI.
     @param category The category of the POI.
     @param roomNumber The room number of the POI.
     @param floor The floor number of the POI.
     @param building The building of the POI.
     */
    public pointsOfInterest(String name, int xCoord, int yCoord, categories category, String roomNumber, int floor, buildings building){
        this.name = name;
        this.yCoord = yCoord;
        this.xCoord = xCoord;
        this.description = "This POI is located in "+building+" on floor "+floor+". " +
                "This POI is found at room number "+ roomNumber;
        this.category = category;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.building = building;

    }
// GETTERS

    /**

     Get the description of the POI.
     @return The description of the POI.
     */
    public String getDescription() {
        return description;
    }
    /**

     Get the category of the POI.
     @return The category of the POI.
     */
    public categories getCategory() {
        return category;
    }
    /**

     Get the floor number of the POI.
     @return The floor number of the POI.
     */
    public int getFloor() {
        return floor;
    }
    /**

     Get the building of the POI.
     @return The building of the POI.
     */
    public buildings getBuilding() {
        return building;
    }
    /**

     Get the name of the POI.
     @return The name of the POI.
     */
    public String getName() {
        return name;
    }
    /**

     Get the room number of the POI.
     @return The room number of the POI.
     */
    public String getRoomNumber() {
        return roomNumber;
    }
    /**

     Get the x-coordinate of the POI.
     @return The x-coordinate of the POI.
     */
    public int getXCoord() {
        return xCoord;
    }
    /**

     Get the y-coordinate of the POI.
     @return The y-coordinate of the POI.
     */
    public int getYCoord() {
        return yCoord;
    }

    /**

     Retrieves the path of the image associated with the POI.
     @return The path of the image.
     */
    public String getPath() {
        return "imgs/POIs/" + category + ".png";
    }
    /**

     Sets the name of the POI.
     @param name The name of the POI.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**

     Sets the description of the POI.
     @param description The description of the POI.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**

     Sets the category of the POI.
     @param category The category of the POI.
     */
    public void setCategory(categories category) {
        this.category = category;
    }
    /**
     Sets the fuzzy match score of the POI.
     */
    public void setFuzzyMatchScore(int score) {
        this.matchScore = score;
    }
    /**

     Retrieves the fuzzy match score of the POI.
     @return The fuzzy match score of the POI.
     */
    public int getFuzzyMatchScore() {
        return this.matchScore;
    }
    /**

     Returns a string representation of the POI, which is its name.
     @return The name of the POI.
     */
    @Override
    public String toString() {
        return this.name;
    }

}
