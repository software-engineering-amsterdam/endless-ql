package org.uva.sea;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

class TestFileHelper {

    public final Collection<String> getTestFiles(final String folderLocation) {
        final Collection<String> testLocation = new ArrayList<>();

        final File folder = new File(folderLocation);
        final File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (final File file : listOfFiles) {
                testLocation.add(file.getAbsolutePath());
            }
        }
        return testLocation;
    }
}
