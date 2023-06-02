import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginGUITest {

    private LoginGUI loginGUI;

    @BeforeEach
    void setUp() {
        loginGUI = new LoginGUI();
        loginGUI.getFrame().setVisible(false);
    }

    @Test
    void testUsernameField() {
        JTextField usernameField = loginGUI.getUsername();
        assertNotNull(usernameField);
        assertTrue(usernameField instanceof JTextField);
        assertEquals(315, usernameField.getBounds().x);
        assertEquals(160, usernameField.getBounds().y);
        assertEquals(200, usernameField.getBounds().width);
        assertEquals(30, usernameField.getBounds().height);
    }

    @Test
    void testPasswordField() {
        JPasswordField passwordField = loginGUI.getPassword();
        assertNotNull(passwordField);
        assertTrue(passwordField instanceof JPasswordField);
        assertEquals(315, passwordField.getBounds().x);
        assertEquals(250, passwordField.getBounds().y);
        assertEquals(200, passwordField.getBounds().width);
        assertEquals(30, passwordField.getBounds().height);
    }

    @Test
    void testTitle() {
        JLabel titleLabel = loginGUI.getTitle();
        assertNotNull(titleLabel);
        assertTrue(titleLabel instanceof JLabel);
        assertEquals("SMPLY Navigate", titleLabel.getText());
        assertEquals(250, titleLabel.getBounds().x);
        assertEquals(50, titleLabel.getBounds().y);
        assertEquals(325, titleLabel.getBounds().width);
        assertEquals(75, titleLabel.getBounds().height);
    }

    @Test
    void testIncorrect() {
        JLabel incorrectLabel = loginGUI.getIncorrect();
        assertNotNull(incorrectLabel);
        assertTrue(incorrectLabel instanceof JLabel);
        assertEquals("3 Attempts", incorrectLabel.getText());
        assertEquals(330, incorrectLabel.getBounds().x);
        assertEquals(360, incorrectLabel.getBounds().y);
        assertEquals(200, incorrectLabel.getBounds().width);
        assertEquals(25, incorrectLabel.getBounds().height);
    }

    @Test
    void testRemainingAttempts() {
        assertEquals(3, loginGUI.getRemainingAttempts());
    }

    @Test
    void testLoginButton() {
        JButton loginButton = loginGUI.getLogin();
        assertNotNull(loginButton);
        assertTrue(loginButton instanceof JButton);
        assertEquals("Login", loginButton.getText());
        assertEquals(300, loginButton.getBounds().x);
        assertEquals(315, loginButton.getBounds().y);
        assertEquals(150, loginButton.getBounds().width);
        assertEquals(25, loginButton.getBounds().height);
    }

    @Test
    void testFrame() {
        JFrame frame = loginGUI.getFrame();
        assertNotNull(frame);
        assertTrue(frame instanceof JFrame);
        assertEquals("SMPLY Navigate", frame.getTitle());
        assertEquals(750, frame.getSize().width);
        assertEquals(500, frame.getSize().height);
        assertFalse(frame.isResizable());
    }

}
