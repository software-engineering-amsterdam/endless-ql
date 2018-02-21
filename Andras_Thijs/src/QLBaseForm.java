import javax.swing.*;

public class QLBaseForm {
    private JPanel mainPanel;
    private JLabel formTitle;

    public final void init(){
        JFrame frame = new JFrame("QLForm");

        mainPanel = new JPanel();

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setVisible(true);
    }
}
