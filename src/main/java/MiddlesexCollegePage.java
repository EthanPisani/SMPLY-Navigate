
import org.json.JSONObject;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
/**
 * The MiddlesexCollegePage class represents a page for Middlesex College building.
 * It extends the Building class.
 * @author Daniel, Ethan
 */
public class MiddlesexCollegePage extends Building {

    public MiddlesexCollegePage(JSONObject userData) {
        try {
            buildingType = pointsOfInterest.buildings.MiddlesexCollege;
            mapImage = ImageIO.read(new File("imgs/MiddlesexCollege/MiddlesexCollege-1.png"));
            floor1image = ImageIO.read(new File("imgs/MiddlesexCollege/MiddlesexCollege-1.png"));
            floor2image = ImageIO.read(new File("imgs/MiddlesexCollege/MiddlesexCollege-2.png"));
            floor3image = ImageIO.read(new File("imgs/MiddlesexCollege/MiddlesexCollege-3.png"));
            floor4image = ImageIO.read(new File("imgs/MiddlesexCollege/MiddlesexCollege-4.png"));
            floor5image = ImageIO.read(new File("imgs/MiddlesexCollege/MiddlesexCollege-5.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        createPage("Middlesex College", userData);
    }

    /**
     * Creates the page with buttons for each floor and their corresponding action listeners.
     * @param title The title of the page.
     */
    private void createPage(String title,JSONObject userData) {
        try {
            Building(title, userData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JPanel buttonPanel = new JPanel();
        JButton floor1Button = new JButton("Floor 1");
        JButton floor2Button = new JButton("Floor 2");
        JButton floor3Button = new JButton("Floor 3");
        JButton floor4Button = new JButton("Floor 4");
        JButton floor5Button = new JButton("Floor 5");
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
        floor4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayImage(floor4image);
                updatePOIsOnMap(4,buildingType);
                currentFloor = 4;
                updateTree();
            }
        });
        floor5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayImage(floor5image);
                updatePOIsOnMap(5,buildingType);
                currentFloor = 5;
                updateTree();
            }
        });

        mainbuttonPanel.add(floor1Button);
        mainbuttonPanel.add(floor2Button);
        mainbuttonPanel.add(floor3Button);
        mainbuttonPanel.add(floor4Button);
        mainbuttonPanel.add(floor5Button);
    }
}
