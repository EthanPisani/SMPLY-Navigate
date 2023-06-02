/**

 A class representing the Help Page of the map application.
 @author Olivia
 */
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays the main Help page
 */
public class HelpPageM {

    JFrame frame;
    private JSONObject userData;

    /**
     * Constructs a HelpPageM object with the given JSONObject userData and creates a Help Page.
     *
     * @param userData the JSONObject containing user data
     */
    public HelpPageM(JSONObject userData) {
        this.userData = userData;
        createPage();
    }

    /**
     * Constructs a HelpPageM object and creates a Help Page.
     */
    public HelpPageM() {
        createPage();
    }

    /**
     * Creates the Help Page.
     */
    private void createPage() {
        frame = new JFrame("Help");
        frame.setSize(new Dimension(1000, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        JButton backButton = new JButton("Previous");

        JLabel label1 = new JLabel("    1. To view a map, use the scroll bar located on the right side and bottom of the window.");
        JLabel label2 = new JLabel("    2. To search for a POI, use the search bar on the top of the window press enter or click the search button and then click on the POI to highlight it on the map.");
        JLabel label3 = new JLabel("    3. To maximize the window, click the maximize button located on the top right corner of the window.");
        JLabel label4 = new JLabel("    4. To go to the previous page, click the previous button on the bottom left");
        JLabel label5 = new JLabel("    5. To create or delete a point of interest on the map, click on the POI. (user cannot edit or delete default POI)");
        JLabel label6 = new JLabel("    6. To add a point of interest to the favorites list, click the POI and click on Set as Favorite.");

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainPage(userData);
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}