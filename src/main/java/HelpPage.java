import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**

 HelpPage is a class that creates a help page for a building mapping application.

 It displays instructions on how to use the application and provides buttons to access other functionalities.

 The class extends JPanel.

 @author Olivia, Shuja, Daniel
 @version 1.0

 */
public class HelpPage {

    // Instance variables
    JFrame frame; // The main frame of the application
    private JSONObject userData; // User data in JSON format

    /**

     Constructs a HelpPage object with the given user data.
     @param userData a JSONObject containing user data
     */
    public HelpPage(JSONObject userData) {
        this.userData = userData;
        createPage();
    }
    /**

     Constructs a HelpPage object.
     */
    public HelpPage() {
        createPage();
    }
    // Instance variable
    protected JPanel mainbuttonPanel;

    /**

     Creates and displays the help page.
     */
    private void createPage() {
        // Create and configure the main frame
        frame = new JFrame("Help");
        frame.setSize(new Dimension(700, 200));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        // Create and configure the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Create and add the labels with instructions
        JLabel label1 = new JLabel(" 1. Click on the buttons with building name to enter the map window");
        JLabel label2 = new JLabel(" 2. Close the window and restart to get back to login page, click on the thermometer to see the temperature.");
        JLabel label3 = new JLabel(" 3. There is a 'previous' button on each building map page to come back to main page.");
        JLabel label4 = new JLabel(" For more detail information about the application click about.");

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

// Create and configure the buttons
        JButton backButton = new JButton("Previous");
        JButton aboutButton = new JButton("About");
        JButton contactButton = new JButton("Contact Us");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainPage(userData);
            }
        });
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutScreen(userData);
            }
        });
        contactButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "For questions or support, please email \n zyang763@uwo.ca \n ssayyid@uwo.ca \n zliao56@uwo.ca \n episani2@uwo.ca \n dmasoumi@uwo.ca");
        });

// Create and configure the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//buttonPanel.add(backButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(contactButton);

// Add the main panel and the button panel to the main frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

// Display the main frame
        frame.setVisible(true);
    }

    /**

     Returns the main frame of the HelpPage object.
     @return the main frame
     */
    public JFrame getFrame() {
        return this.frame;
    }
}