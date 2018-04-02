package nl.uva.se.sc.niro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractParserTest {
    protected String formFile;

    public AbstractParserTest(String formFile) {
        this.formFile = formFile;
    }

    protected static Collection<Object> findFormFilesInFolder(String folderName, String extension) {
        String[] qlFormFiles = new File(AbstractParserTest.class.getResource(folderName).getFile()).
                list((dir, name) -> name.endsWith(extension));
        return addFolderNameToFileNames(folderName, qlFormFiles);
    }

    private static Collection<Object> addFolderNameToFileNames(String folderName, String[] fileNames) {
        Collection<Object> qlFormFiles = new ArrayList<>(fileNames.length);
        for (String qlFormName : fileNames) {
            qlFormFiles.add(folderName + qlFormName);
        }
        return qlFormFiles;
    }

    protected CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
    }
}
