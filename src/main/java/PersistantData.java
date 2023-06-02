import org.json.JSONObject;
import org.json.JSONArray;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Used to ensure that Data is persistant
 */
public class PersistantData {
    public void saveUserData(JSONObject userData) {
        String uuid = userData.getString("uuid");
        String fileName = uuid + ".json";
        FileWriter file = null;

        try {
            file = new FileWriter("data/"+fileName);
            file.write(userData.toString(2));

        } catch (IOException e) {
        }finally {
            if (file!= null){
                try{
                    file.close();
                }catch (IOException e){
                }
            }
        }
    }

    public static boolean validUser(String Username){
        String fileName = "data/main.json";
        boolean hasUser = false;
        try (FileReader reader = new FileReader(fileName)) {
            int fileSize = (int) new File(fileName).length();
            char[] buffer = new char[fileSize];
            reader.read(buffer, 0, fileSize);
            String fileContent = new String(buffer);
            JSONObject mainData = new JSONObject(fileContent);
            hasUser = mainData.has(Username);
        } catch (IOException e) {
        }
        return hasUser;
    }


    public static JSONObject readUserData(String uuid) {
        String fileName = "data/"+ uuid + ".json";
        JSONObject userData = null;

        try (FileReader reader = new FileReader(fileName)) {
            int fileSize = (int) new File(fileName).length();
            char[] buffer = new char[fileSize];
            reader.read(buffer, 0, fileSize);
            String fileContent = new String(buffer);
            userData = new JSONObject(fileContent);
        } catch (IOException e) {
        }

        return userData;
    }
    public static ArrayList<JSONObject> convertToArrayList(JSONObject pointsOfInterest) {
        ArrayList<JSONObject> poiList = new ArrayList<>();
        JSONArray poiArray = pointsOfInterest.getJSONArray("pointsOfInterest");

        for (int i = 0; i < poiArray.length(); i++) {
            JSONObject poi = poiArray.getJSONObject(i);
            poiList.add(poi);
        }

        return poiList;
    }
    public static String getUUID(String username) {
        String fileName = "data/main.json";
        String uuid = null;

        try (FileReader reader = new FileReader(fileName)) {
            int fileSize = (int) new File(fileName).length();
            char[] buffer = new char[fileSize];
            reader.read(buffer, 0, fileSize);
            String fileContent = new String(buffer);
            JSONObject mainData = new JSONObject(fileContent);

             uuid = mainData.getString(username);

        } catch (IOException e) {
        }

        return uuid;
    }
}

