import org.apache.commons.text.similarity.FuzzyScore;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * The AviationResearchPage class represents a page for aviation research in a building, extending the Building class.
 * It contains methods for creating the page, updating the overlay image, updating points of interest on the map, and updating the tree.
 * @author Daniel
 */
public class AviationResearchPage extends Building {
    private JSONObject userData;


    /**
     * Constructs a new AviationResearchPage object.
     * Initializes the building type, map image, floor 1 image, and floor 2 image using ImageIO.read() method.
     * Sets up the page by calling the createPage() method with the specified title.
     */
    public AviationResearchPage() {
        try {
            isAdmin = false;
            buildingType = pointsOfInterest.buildings.AdvancedFacilityForAvianResearch;
            mapImage = ImageIO.read(new File("imgs/AdvancedFacilityForAviaResearch/AdvancedFacilityForAviaResearch-1.png"));
            floor1image = ImageIO.read(new File("imgs/AdvancedFacilityForAviaResearch/AdvancedFacilityForAviaResearch-1.png"));
            floor2image = ImageIO.read(new File("imgs/AdvancedFacilityForAviaResearch/AdvancedFacilityForAviaResearch-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createPage("Aviation Research");
    }

    public AviationResearchPage(JSONObject userData) {
        try {
            this.userData = userData;
            buildingType = pointsOfInterest.buildings.AdvancedFacilityForAvianResearch;
            mapImage = ImageIO.read(new File("imgs/AdvancedFacilityForAviaResearch/AdvancedFacilityForAviaResearch-1.png"));
            floor1image = ImageIO.read(new File("imgs/AdvancedFacilityForAviaResearch/AdvancedFacilityForAviaResearch-1.png"));
            floor2image = ImageIO.read(new File("imgs/AdvancedFacilityForAviaResearch/AdvancedFacilityForAviaResearch-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createPage("Aviation Research");
    }

    /**
     * Creates the page with the specified title.
     * Sets up buttons for floor 1 and floor 2, and their corresponding action listeners.
     * Adds the buttons to the main button panel.
     * @param title The title of the page.
     */
    private void createPage(String title) {
        try {
            Building(title, userData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JButton floor1Button = new JButton("Floor 1");
        JButton floor2Button = new JButton("Floor 2");
        floor1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayImage(floor1image);
                updatePOIsOnMap(1, buildingType);
                currentFloor = 1;
                updateTree();
            }
        });
        floor2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayImage(floor2image);
                updatePOIsOnMap(2, buildingType);
                currentFloor = 2;
                updateTree();
            }
        });
        mainbuttonPanel.add(floor1Button);
        mainbuttonPanel.add(floor2Button);
    }
}
