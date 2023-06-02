import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class HelpPageMTest {

    @Test
    public void testCreatePage() {
        JSONObject test1 = new JSONObject();
        HelpPageM helpPage = new HelpPageM();

        JFrame frame = helpPage.frame;
        assertNotNull(frame, "Frame should not be null");
        assertEquals("Help", frame.getTitle(), "Title should be 'Help'");
        assertEquals(JFrame.DISPOSE_ON_CLOSE, frame.getDefaultCloseOperation(), "CloseOperation should be JFrame.DISPOSE_ON_CLOSE");
        assertFalse(frame.isResizable(), "Frame should not be resizable");

        Container contentPane = frame.getContentPane();
        assertNotNull(contentPane, "ContentPane should not be null");
        assertTrue(contentPane instanceof JPanel, "ContentPane should be a JPanel");


    }

}
