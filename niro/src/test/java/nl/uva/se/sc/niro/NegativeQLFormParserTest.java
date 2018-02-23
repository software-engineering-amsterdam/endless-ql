package nl.uva.se.sc.niro;

import nl.uva.se.sc.niro.parser.QLFormParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;

import static junit.framework.TestCase.fail;

@RunWith(Parameterized.class)
public class NegativeQLFormParserTest extends AbstractQLFormParserTest {

    public NegativeQLFormParserTest(String formFile) {
        super(formFile);
    }

    @Parameterized.Parameters(name = "Parsing: {0}")
    public static Collection<Object> parameters() {
        return findFormFilesInFolder("/negative/");
    }

    @Test
    public void parserTest() throws IOException {
        QLFormParser.parse(toCharStream(formFile));
        if (QLFormParser.getParseErrors().isEmpty()) {
            fail("No error was reported but at least one should have been reported!");
        }
    }

}
