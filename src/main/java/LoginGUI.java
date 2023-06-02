import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * Default Login GUI
 */
public class LoginGUI implements ActionListener {
    private JFrame frame;
    private JLabel title;
    private JLabel incorrect;

    protected String enteredUsername;
    protected JLabel user;
    private JLabel pass;
    private JButton login;
    private JButton newUser;



    protected JTextField username;
    private JPasswordField password;
    private int remainingAttempts;
    private  JLabel temperatureLabel;
    private JSONObject userData;

    /**
     * The LoginGUI class represents a graphical user interface for a login screen.
     * It sets up the visual displays, including labels, text fields, buttons, and weather information.
     * It also handles actions for the login and new user buttons, and updates the weather information
     * and icon based on the current temperature retrieved from a weather API.
     *
     * @author leo
     */
    public LoginGUI() {

        //Setting up the visuals displays
        this.frame = new JFrame("SMPLY Navigate");
        this.frame.setSize(new Dimension(750, 500));
        this.frame.setLayout(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);


        this.title = new JLabel();
        this.title.setText("SMPLY Navigate");
        this.title.setFont(new Font("Consolas", Font.BOLD, 30));
        this.title.setBounds(250, 50, 325, 75);
        this.frame.add(this.title);

        this.user = new JLabel("Username: ");
        this.pass = new JLabel("Password: ");
        this.incorrect = new JLabel("");

        this.user.setBounds(190, 160, 125, 30);
        this.user.setFont(new Font("Consolas", Font.BOLD, 20));
        this.frame.add(this.user);

        this.pass.setBounds(190, 250, 125, 30);
        this.pass.setFont(new Font("Consolas", Font.BOLD, 20));
        this.frame.add(this.pass);

        this.username = new JTextField();
        this.username.setPreferredSize(new Dimension(125, 30));
        this.username.setBounds(315, 160, 200, 30);
        this.frame.add(this.username);

        this.password = new JPasswordField();
        //this.password.setPreferredSize(new Dimension(125, 30));
        this.password.setBounds(315, 250, 200, 30);
        this.frame.add(this.password);

        this.login = new JButton("Login");
        this.login.addActionListener(this);
        this.login.setBounds(300, 315, 150, 25);
        this.login.setFocusable(false);
        this.frame.add(this.login);

        this.incorrect = new JLabel("3 Attempts");
        this.incorrect.setFont(new Font("Consolas", Font.BOLD, 14));
        this.incorrect.setBounds(330, 360, 200, 25);
        this.frame.add(this.incorrect);

        this.remainingAttempts = 3; //3 attempts before program closes

        // temperature

        String temperature = WeatherAPI.getTemperature();
        // Set default icon as cold.png
        this.temperatureLabel = new JLabel("cannot connect");
        if (!temperature.equals("null")) {


            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Get the current temperature in Celsius
                    String temperature = WeatherAPI.getTemperature();
                    // Update the temperature label text
                    temperatureLabel.setText(temperature + " °C");
                    if (!temperature.equals("null")) {
                        // Update the icon based on temperature value

                        float temp = Float.parseFloat(temperature);
                        if (temp < 0.0f) {
                            temperatureLabel.setIcon(resizeIcon(new ImageIcon("imgs/WeatherAPI/Cold.png"), 100, 100));
                        } else if (temp < 25.0f) {
                            temperatureLabel.setIcon(resizeIcon(new ImageIcon("imgs/WeatherAPI/Norm.png"), 100, 100));
                        } else {
                            temperatureLabel.setIcon(resizeIcon(new ImageIcon("imgs/WeatherAPI/Hot.png"), 100, 100));
                        }

                        // Print the temperature to the console
                        temperatureLabel.setToolTipText(temperature + " °C");
                    }
                    else {
                        temperatureLabel.setToolTipText("cannot connect");
                        temperatureLabel.setIcon(resizeIcon(new ImageIcon("imgs/WeatherAPI/x.png"),100,100));

                    }
                }
            }, 0, 60 * 1000); // Schedule the task to run every 1 minute (60 seconds * 1000 milliseconds)


        }
        this.temperatureLabel.setBounds(600, 300, 100, 100); // Update the height of label to accommodate icon
        this.frame.add(this.temperatureLabel);
        this.frame.setVisible(true);
    }
    /**
     * Resizes an ImageIcon to the specified width and height.
     *
     * @param icon The original ImageIcon to be resized.
     * @param width The desired width of the resized ImageIcon.
     * @param height The desired height of the resized ImageIcon.
     * @return The resized ImageIcon.
     */
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    /**
     *
     * This method is the actionPerformed() function that is called when an action event occurs,
     * such as a button click or a menu item selection, in the application.
     * It performs various checks such as validating username, hashing and comparing passwords, updating UI for incorrect attempts,
     * and disabling login button after maximum attempts reached. It also calls a MainPage() function for successful login.
     * @param e The ActionEvent object that represents the action event.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String usernameText = username.getText();
        String passwordText = new String(password.getPassword());
        // check if user exists
        if (PersistantData.validUser(usernameText)){
            userData =  PersistantData.readUserData(PersistantData.getUUID(usernameText));
            //System.out.println(BCrypt.hashpw(passwordText, BCrypt.gensalt(12)));
            if (BCrypt.checkpw(passwordText, userData.getString("password"))) {
                frame.setVisible(false);


                new MainPage(userData);
            }
        } else {
            this.remainingAttempts--;
            this.incorrect.setText("***Incorrect Credentials, " + this.remainingAttempts + " Remaining Attempts");
            this.incorrect.setBounds(200, 360, 400, 25);

            if (this.remainingAttempts == 0) {
                this.login.setEnabled(false);
            }

        }
    }
    //getter methods
    /**
     * Returns the JFrame object associated with this instance.
     * @return the JFrame object
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Returns the JTextField object representing the username input field.
     * @return the JTextField object for the username
     */
    public JTextField getUsername() {
        return this.username;
    }

    /**
     * Returns the JPasswordField object representing the password input field.
     * @return the JPasswordField object for the password
     */
    public JPasswordField getPassword() {
        return this.password;
    }

    /**
     * Returns the JLabel object representing the title of the login page.
     * @return the JLabel object for the title
     */
    public JLabel getTitle() {
        return this.title;
    }

    /**
     * Returns the JLabel object representing the incorrect login message.
     * @return the JLabel object for the incorrect message
     */
    public JLabel getIncorrect() {
        return this.incorrect;
    }

    /**
     * Returns the number of remaining login attempts.
     * @return the number of remaining attempts
     */
    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    /**
     * Returns the JButton object representing the login button.
     * @return the JButton object for the login button
     */
    public JButton getLogin() {
        return login;
    }


}
