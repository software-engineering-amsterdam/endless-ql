package nl.uva.se.sc.niro;

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

    protected static Collection<Object> findFilesInFolder(String folderName) {
        String pathname = AbstractQLFormParserTest.class.getResource(folderName).getFile();
        System.out.println(pathname);
        String[] qlFormNames = new File(pathname).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ql");
            }
        });
        Collection<Object> qlFormFiles = new ArrayList<>(qlFormNames.length);
        for (String qlFormName : qlFormNames) {
            qlFormFiles.add(folderName + qlFormName);
        }
        return qlFormFiles;
    }

    protected CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
    }
}
