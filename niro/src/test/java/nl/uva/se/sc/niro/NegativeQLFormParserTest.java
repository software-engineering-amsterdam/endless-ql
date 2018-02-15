package nl.uva.se.sc.niro;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void warnUserForErrorInConsole() {
        System.out.printf("%nPlease be aware that we expect error messages being printed to the console!%n%n");
    }

    @AfterClass
    public static void notifyUserThatErrorsAreBadAgain() {
        System.out.printf("%nTests have been run, so you can be worried when you see error messages appearing...%n%n");
    }

    @Parameterized.Parameters(name = "Parsing: {0}")
    public static Collection<Object> parameters() {
        return findFilesInFolder("/negative/");
    }

    @Test
    public void parserTest() throws IOException {
        parser.QLFormParser$.MODULE$.parse(toCharStream(formFile));
        if (!ErrorListener.errorReported) {
            fail("Something went wrong, see the console for more information!");
        }
    }


}
