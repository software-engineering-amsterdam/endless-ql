package qls.gui.view;

import qls.gui.controller.GuiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class InitialPanel extends JPanel {
    public InitialPanel(GuiController guiController) {
        this.setPreferredSize(new Dimension(800, 600));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        JButton openQlButton = new JButton("Open QL file...");
        openQlButton.addActionListener((ActionEvent e) -> {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                guiController.setQlFilePath(selectedFile.getAbsolutePath());
            }
        });
        this.add(openQlButton);

        JButton openQlsButton = new JButton("Open QLS file...");
        openQlsButton.addActionListener((ActionEvent e) -> {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                guiController.setQlsFilePath(selectedFile.getAbsolutePath());
            }
        });
        this.add(openQlsButton);
    }
}
