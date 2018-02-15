package nl.uva.se.sc.niro;

import nl.uva.se.sc.niro.parser.ErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Before;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AbstractQLFormParserTest {
    protected String formFile;

    public AbstractQLFormParserTest(String formFile) {
        this.formFile = formFile;
    }

    @Before
    public void clearErrors() {
        ErrorListener.errorReported = false;
    }

    protected static Collection<Object> findFormFilesInFolder(String folderName) {
        String[] qlFormFiles = new File(AbstractQLFormParserTest.class.getResource(folderName).getFile()).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ql");
            }
        });
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
