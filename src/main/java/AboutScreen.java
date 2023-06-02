/**

 The AboutScreen class displays information about the SMPLY Navigate application and its team members.
 @author Olivia
 */
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gui for the About Us section
 */
public class AboutScreen {
    JFrame frame;
    private JSONObject userData;

    /**
     * Constructs an AboutScreen object with the given user data and calls the createPage method.
     * @param userData the user data to be displayed
     */
    public AboutScreen(JSONObject userData) {
        this.userData = userData;
        createPage();
    }

    /**
     * Creates the page layout and adds the components for the About Screen.
     */
    private void createPage() {
        frame = new JFrame("About");
        frame.setSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        JLabel appNameLabel = new JLabel("  Application Name: SMPLY Navigate");
        JLabel versionLabel = new JLabel("  Version 1.0");
        JLabel releaseDateLabel = new JLabel("  Released on April 4, 2023");
        JLabel teamLabel = new JLabel(" Team Members:");
        JLabel member1Label = new JLabel("      - Daniel Masoumi Rad");
        JLabel member2Label = new JLabel("      - Ethan Lawrence Samuel Pisani");
        JLabel member3Label = new JLabel("      - Zhangqing Yang");
        JLabel member4Label = new JLabel("      - Shuja Sayyid");
        JLabel member5Label = new JLabel("      - ZihYu Liao");

        panel.add(appNameLabel);
        panel.add(versionLabel);
        panel.add(releaseDateLabel);
        panel.add(teamLabel);
        panel.add(member1Label);
        panel.add(member2Label);
        panel.add(member3Label);
        panel.add(member4Label);
        panel.add(member5Label);

        JButton backButton = new JButton("Previous");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainPage(userData);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //buttonPanel.add(backButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Returns the JFrame object for the AboutScreen.
     * @return the JFrame object for the AboutScreen
     */
    public JFrame getFrame() {
        return frame;
    }
}
