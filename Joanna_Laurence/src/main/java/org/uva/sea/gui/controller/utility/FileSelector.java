package org.uva.sea.gui.controller.utility;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public final class FileSelector {

    public static File getFile(final String title, final String fileType, final String fileExtension) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(fileType, fileExtension),
                new ExtensionFilter("All Files", "*.*")
        );
        return fileChooser.showOpenDialog(null);
    }
}
