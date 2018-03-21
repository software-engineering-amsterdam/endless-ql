package org.uva.sea.gui.newImpl;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class FileSelector {

    private final String title;
    private final String fileType;
    private final String fileExtension;

    public FileSelector(String title, String fileType, String fileExtension) {
        this.title = title;
        this.fileType = fileType;
        this.fileExtension = fileExtension;
    }

    public File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(this.title);
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(this.fileType, this.fileExtension),
                new ExtensionFilter("All Files", "*.*"));
        return fileChooser.showOpenDialog(null);
    }
}
