package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class QLSCheckerTest {

    private static final String QL_BASE = "src/test/java/nl/uva/js/qlparser/logic/testdata/ql_input.jsql";
    private static final String QLS_BASE = "src/test/java/nl/uva/js/qlparser/logic/testdata/qls_input.jsqls";

    private QLSChecker checker;

    @Before
    public void setup(){
        this.checker = new QLSChecker();
    }


    @Test
    public void testCheckerApprovesOfProperFormAndStylesheet(){
        Form form             = FormBuilder.parseFormFromLocation(QL_BASE);
        Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(QLS_BASE);

        ArrayList<String> errors = checker.checkForErrors(form, stylesheet);
        assertEquals(0, errors.size());
    }

}
