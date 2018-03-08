package org.uva.sea.gui.render;

import javafx.stage.FileChooser;

import java.io.File;

public class FileSelector {

    private String title;
    private String fileType;
    private String fileExtension;

    public FileSelector(String title, String fileType, String fileExtension) {
        this.title = title;
        this.fileType = fileType;
        this.fileExtension = fileExtension;
    }

    public File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(this.title);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(this.fileType, this.fileExtension),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        return fileChooser.showOpenDialog(null);
    }
}
