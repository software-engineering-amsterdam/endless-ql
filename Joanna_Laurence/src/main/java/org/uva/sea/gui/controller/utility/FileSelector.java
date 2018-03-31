package org.uva.sea.gui.controller.utility;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class FileSelector {

    public static File getFile(String title, String fileType, String fileExtension) {
        FileChooser fileChooser = createFileChooser(title, fileType, fileExtension);
        return fileChooser.showOpenDialog(null);
    }

    public static File saveFile(String title, String fileType, String fileExtension) {
        FileChooser fileChooser = createFileChooser(title, fileType, fileExtension);
        return fileChooser.showSaveDialog(null);
    }

    private static FileChooser createFileChooser(String title, String fileType, String fileExtension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(fileType, fileExtension),
                new ExtensionFilter("All Files", "*.*")
        );
        return fileChooser;
    }


}
