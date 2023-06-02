import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class HelpPageTest {
    @Test
    void testCreatePage() {
        HelpPage helpPage = new HelpPage();
        assertNotNull(helpPage);

        // Check if the frame is not null and has the correct title
        assertNotNull(helpPage.frame);
        assertEquals(helpPage.frame.getTitle(), "Help");

        // Check if the panel is not null and has the correct layout and number of components
        assertNotNull(helpPage.frame.getContentPane().getComponent(0));
        assertTrue(helpPage.frame.getContentPane().getComponent(0) instanceof JPanel);
        JPanel panel = (JPanel) helpPage.frame.getContentPane().getComponent(0);
        assertEquals(panel.getLayout().getClass().getSimpleName(), "GridLayout");
        assertEquals(4, panel.getComponentCount());

    }
}
