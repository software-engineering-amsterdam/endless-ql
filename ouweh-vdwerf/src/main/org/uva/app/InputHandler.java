package org.uva.app;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class InputHandler {

    private final Logger logger;

    InputHandler() {
        this.logger = Logger.getGlobal();
    }

    public String getUserInput(String type) {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "QL & QLS Questionnaires", type);
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return readFile(chooser.getSelectedFile().getName());
        } else {
            logger.severe("File selection unsuccessful");
        }
        return "";
    }

    public String readFile(String location) {
        String result = "";
        try {
            byte[] a = Files.readAllBytes(Paths.get(location));
            result = new String(a);
        } catch (IOException e) {
            logger.severe(String.format("There was an error reading the input file: %s", e));
            e.printStackTrace();
        }
        return result;
    }
}
