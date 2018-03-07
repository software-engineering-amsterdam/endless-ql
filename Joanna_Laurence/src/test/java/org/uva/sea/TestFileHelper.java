package org.uva.sea;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestFileHelper {

    public Collection<String> getTestFiles(String folderLocation) {
        List<String> testLocation = new ArrayList<>();

        File folder = new File(folderLocation);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                testLocation.add(file.getAbsolutePath());
            }
        }
        return testLocation;
    }
}
