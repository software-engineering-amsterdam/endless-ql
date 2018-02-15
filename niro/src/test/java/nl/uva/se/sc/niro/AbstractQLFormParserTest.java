package nl.uva.se.sc.niro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Before;

import java.io.IOException;

public class AbstractQLFormParserTest {
    protected String formFile;

    public AbstractQLFormParserTest(String formFile) {
        this.formFile = formFile;
    }

    @Before
    public void clearErrors() {
        ErrorListener.errorReported = false;
    }

    protected CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
    }
}
