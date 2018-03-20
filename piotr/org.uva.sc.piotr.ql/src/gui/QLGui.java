package gui;

import gui.controller.FileOpenActionEvent;

import javax.swing.*;
import java.awt.*;

public class QLGui {
    public QLGui() {

        JFrame frame = new JFrame("QL Form GUI");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton openingButton = new JButton("Open file...");
        JPanel openingPanel = new JPanel();
        openingPanel.setPreferredSize(new Dimension(200, 200));

        openingPanel.add(openingButton);
        frame.getContentPane().add(openingPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        openingButton.addActionListener(new FileOpenActionEvent(frame));

    }

}
