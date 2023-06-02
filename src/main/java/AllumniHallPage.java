import org.apache.commons.text.similarity.FuzzyScore;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * The AllumniHallPage class represents a specific page for the Alumni Hall building
 * in a graphical user interface. It extends the Building class and provides functionality
 * for displaying different floors of the building and updating points of interest on a map.
 * @author Daniel
 */
public class AllumniHallPage extends Building {

    private JSONObject userData;


    /**
     * Constructs a new AllumniHallPage object. Load images for different floors of the
     * building and sets up the page with buttons for each floor.
     */
    public AllumniHallPage() {
        try {

            buildingType = pointsOfInterest.buildings.AlumniHall;
            mapImage = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-1.png"));
            floor1image = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-1.png"));
            floor2image = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-2.png"));
            floor3image = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createPage("Alumni Hall");
    }

    public AllumniHallPage(JSONObject userData) {
        try {
            this.userData = userData;
            buildingType = pointsOfInterest.buildings.AlumniHall;
            mapImage = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-1.png"));
            floor1image = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-1.png"));
            floor2image = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-2.png"));
            floor3image = ImageIO.read(new File("imgs/AlumniHall/AlumniHall-3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createPage("Alumni Hall");
    }

    /**
     * Private helper method to create the page with buttons for each floor of the building.
     *
     * @param title the title of the page
     */
    private void createPage(String title) {
        try {
            Building(title, userData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create buttons for each floor
        JButton floor1Button = new JButton("Floor 1");
        JButton floor2Button = new JButton("Floor 2");
        JButton floor3Button = new JButton("floor 3");

        // Add action listeners to the buttons
        floor1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayImage(floor1image);
                updatePOIsOnMap(1,buildingType);
                currentFloor = 1;
                updateTree();
            }
        });
        floor2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayImage(floor2image);
                updatePOIsOnMap(2,buildingType);
                currentFloor = 2;
                updateTree();
            }
        });
        floor3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayImage(floor3image);
                updatePOIsOnMap(3,buildingType);
                currentFloor = 3;
                updateTree();
            }
        });

        // Add buttons to the main button panel
        mainbuttonPanel.add(floor1Button);
        mainbuttonPanel.add(floor2Button);
        mainbuttonPanel.add(floor3Button);
    }
}
