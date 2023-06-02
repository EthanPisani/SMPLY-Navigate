import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MainPageTest {

    private MainPage mainPage;
    private JFrame frame;
    private JSONObject userData;

    @BeforeEach
    void setUp() {
        userData =  PersistantData.readUserData("admin");
        mainPage = new MainPage(userData);
        frame = mainPage.frame;
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }

    @Test
    void testBuilding1ButtonActionPerformed() {
        JButton building1Button = findButtonByText(frame, "Middlesex College");
        assertNotNull(building1Button, "Button not found");
        building1Button.doClick();
        assertFalse(frame.isVisible(), "Frame should not be visible after button click");
    }

    @Test
    void testBuilding2ButtonActionPerformed() {
        JButton building2Button = findButtonByText(frame, "Alumni Hall");
        assertNotNull(building2Button, "Button not found");
        building2Button.doClick();
        assertFalse(frame.isVisible(), "Frame should not be visible after button click");

    }

    @Test
    void testBuilding3ButtonActionPerformed() {
        JButton building3Button = findButtonByText(frame, "Aviation Research");
        assertNotNull(building3Button, "Button not found");
        building3Button.doClick();
        assertFalse(frame.isVisible(), "Frame should not be visible after button click");

    }

    @Test
    void testHelpButtonActionPerformed() {
        JButton helpButton = findButtonByText(frame, "Help");
        assertNotNull(helpButton, "Button not found");
        helpButton.doClick();
        assertTrue(new HelpPage().getFrame().isVisible(), "New frame should be visible after button click");
    }

    private JButton findButtonByText(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals(text)) {
                return (JButton) component;
            } else if (component instanceof Container) {
                JButton button = findButtonByText((Container) component, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

}
