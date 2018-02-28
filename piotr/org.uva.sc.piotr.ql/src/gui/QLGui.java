package gui;

import javax.swing.*;
import java.awt.*;

public class QLGui {
    public QLGui() {
        JFrame frame = new JFrame("QL Form");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to QL Form");
        frame.getContentPane().add(label, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
