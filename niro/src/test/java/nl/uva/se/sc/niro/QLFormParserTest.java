package nl.uva.se.sc.niro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.fail;

@RunWith(Parameterized.class)
public class QLFormParserTest {
    private String formFile;

    public QLFormParserTest(String formFile) {
        this.formFile = formFile;
    }

    @Before
    public void clearErrors() {
        ErrorListener.errorReported = false;
    }

    @Parameterized.Parameters(name = "Parsing: {0}")
    public static Collection<Object> parameters() {
        Object[] formFiles = {
                "/positive/simple.ql",
                "/positive/not-conditional-if.ql",
                "/positive/simple-conditional-if.ql",
                "/positive/simple-conditional-if-else.ql",
                "/positive/nested-conditional.ql",
                "/positive/expression-conditional-if.ql",
                "/positive/expression-conditional-if-else.ql",
                "/positive/nested-expression.ql"
        };
        return Arrays.asList(formFiles);
    }

    @Test
    public void parserTest() throws IOException {
        parser.QLFormParser$.MODULE$.parse(toCharStream(formFile));
        if (ErrorListener.errorReported) {
            fail("Something went wrong, see the console for more information!");
        }
    }


    private CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
    }

}
