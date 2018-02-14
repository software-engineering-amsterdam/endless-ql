import javax.swing.*;
import java.awt.*;

public class FormBuilder {
    private JFrame frame; //The frame on which the form is located
    private JPanel panel; //The panel on which the widgets are located
    private int FRAMEHEIGHT = 800; //The height of the GUI
    private int FRAMEWIDTH = 800; //The width of the GUI

    /**
     * constructor method
     * initializes the building process of the form
     */
    public FormBuilder() {
        buildForm();
    }

    /**
     * buildForm() method
     * initializes the building process for all widgets
     */
    private void buildForm() {
        //Build the frame and panel of the form
        buildFrame();
        buildPanel();

        //Add a scroll pane to the form
        JScrollPane scrollPane = new JScrollPane(panel);
        this.frame.getContentPane().add(scrollPane);

    }

    /**
     * buildFrame() method
     * builds the frame
     */
    private void buildFrame() {
        this.frame = new JFrame("Questionnaire (QL)");
        this.frame.setVisible(true);
        this.frame.setBounds(0,0,FRAMEHEIGHT, FRAMEWIDTH);
    }

    /**
     * buildFrame() method
     * builds the panel
     */
    private void buildPanel() {
        this.panel = new JPanel(new GridBagLayout());
    }
}
