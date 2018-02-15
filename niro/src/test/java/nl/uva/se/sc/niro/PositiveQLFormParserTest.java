package nl.uva.se.sc.niro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;

import static junit.framework.TestCase.fail;

@RunWith(Parameterized.class)
public class PositiveQLFormParserTest extends AbstractQLFormParserTest {

    public PositiveQLFormParserTest(String formFile) {
        super(formFile);
    }

    @Parameterized.Parameters(name = "Parsing: {0}")
    public static Collection<Object> parameters() {
        return findFormFilesInFolder("/positive/");
    }

    @Test
    public void parserTest() throws IOException {
        parser.QLFormParser$.MODULE$.parse(toCharStream(formFile));
        if (ErrorListener.errorReported) {
            fail("Something went wrong, see the console for more information!");
        }
    }


}
