import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**

 The MainPage class represents the main page of an application, which implements the ActionListener interface to handle button clicks.

 It displays a JFrame window with buttons for different buildings and a help button. When a button is clicked, the corresponding action

 is performed, such as opening a new window for a specific building or displaying a help page.
 */
public class MainPage implements ActionListener {
    JFrame frame; // The main JFrame window

    private JSONObject userData;

    /**
     * Constructor for MainPage class.
     * Sets up the JFrame window and adds buttons for different buildings and a help button.
     */
    public MainPage() {

        frame = new JFrame("Main Page"); // Create a new JFrame window with title "Main Page"
        frame.setSize(new Dimension(750, 500)); // Set the size of the window to 750x500 pixels
        frame.setLayout(null); // Set the layout manager to null
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
        frame.setResizable(false); // Set the window to not be resizable

// Create buttons for different buildings and add them to the frame
        JButton building1Button = new JButton("Middlesex College");
        building1Button.setBounds(300, 150, 150, 40);
        building1Button.addActionListener(this);
        frame.add(building1Button);

        JButton building2Button = new JButton("Alumni Hall");
        building2Button.setBounds(300, 220, 150, 40);
        building2Button.addActionListener(this);
        frame.add(building2Button);

        JButton building3Button = new JButton("Aviation Research");
        building3Button.setBounds(300, 290, 150, 40);
        building3Button.addActionListener(this);
        frame.add(building3Button);

        JButton helpButton = new JButton("Help");
        helpButton.setBounds(300, 360, 150, 40);
        helpButton.addActionListener(this);
        frame.add(helpButton);

        frame.setVisible(true); // Set the frame to be visible
    }


    public MainPage(JSONObject userData) {
        this.userData = userData;

        frame = new JFrame("Main Page"); // Create a new JFrame window with title "Main Page"
        frame.setSize(new Dimension(750, 500)); // Set the size of the window to 750x500 pixels
        frame.setLayout(null); // Set the layout manager to null
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
        frame.setResizable(false); // Set the window to not be resizable

// Create buttons for different buildings and add them to the frame
        JButton building1Button = new JButton("Middlesex College");
        building1Button.setBounds(300, 150, 150, 40);
        building1Button.addActionListener(this);
        frame.add(building1Button);

        JButton building2Button = new JButton("Alumni Hall");
        building2Button.setBounds(300, 220, 150, 40);
        building2Button.addActionListener(this);
        frame.add(building2Button);

        JButton building3Button = new JButton("Aviation Research");
        building3Button.setBounds(300, 290, 150, 40);
        building3Button.addActionListener(this);
        frame.add(building3Button);

        JButton helpButton = new JButton("Help");
        helpButton.setBounds(300, 360, 150, 40);
        helpButton.addActionListener(this);
        frame.add(helpButton);

        frame.setVisible(true); // Set the frame to be visible
    }

    /**
     * ActionListener implementation for handling button clicks.
     * <p>
     * Performs corresponding actions when buttons are clicked, such as opening a new window for a specific building or displaying a help page.
     *
     * @param e The ActionEvent object representing the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) { // Check if the event source is a JButton
            JButton clickedButton = (JButton) e.getSource(); // Cast the event source to a JButton
            String buttonText = clickedButton.getText(); // Get the text on the clicked button
            // Perform actions based on the text of the clicked button
            switch (buttonText) {
                case "Middlesex College":
                    frame.setVisible(false); // Hide the main frame
                    new MiddlesexCollegePage(userData); // Open a new window for Middlesex College page
                    break;
                case "Alumni Hall":
                    frame.setVisible(false); // Hide the main frame
                    new AllumniHallPage(userData); // Open a new window for Alumni Hall page
                    break;
                case "Aviation Research":
                    frame.setVisible(false); // Hide the main frame
                    new AviationResearchPage(userData); // Open a new window for Aviation Research page
                    break;
                case "Help":
                    new HelpPage(userData); // Open a new window for Help page
                    break;
                default:
                    //System.out.println("Unexpected button click: " + buttonText); // Print a message for unexpected button clicks
                    break;
            }
        }
    }
}