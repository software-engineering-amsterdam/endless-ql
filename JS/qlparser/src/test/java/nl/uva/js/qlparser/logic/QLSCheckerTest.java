package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QLSCheckerTest {

    private static final String QL_BASE  = "src/test/resources/ql/ql_input.jsql";
    private static final String QLS_DIR  = "src/test/resources/qls/";
    private static final String QLS_BASE = QLS_DIR + "qls_input.jsqls";

    private static final String UNPLACED        = QLS_DIR + "unplaced.jsqls";
    private static final String INVALID_EXPR    = QLS_DIR + "invalid_expression.jsqls";
    private static final String DUPLICATE       = QLS_DIR + "duplicate.jsqls";
    private static final String INVALID_WIDGET  = QLS_DIR + "invalid_widget.jsqls";
    private static final String INVALID_DEFAULT = QLS_DIR + "invalid_default.jsqls";

    private QLSChecker checker;
    private Form form;

    @Before
    public void setup() {
        this.checker = new QLSChecker();
        this.form = FormBuilder.parseFormFromLocation(QL_BASE);
    }

    @Test
    public void testCheckerApprovesOfProperFormAndStylesheet() {
        Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(QLS_BASE);
        List<String> errors = checker.checkForErrors(form, stylesheet);
        assertEquals(0, errors.size());
    }

    @Test
    public void testUnplacedFormExpression() {
        Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(UNPLACED);
        assertError(stylesheet, QLSChecker.UNPLACED_FORM_EXPRESSION);
    }

    @Test
    public void testInvalidFormExpression() {
        Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(INVALID_EXPR);
        assertError(stylesheet, QLSChecker.INVALID_FORM_EXPRESSION);
    }

    @Test
    public void testDuplicateReferences() {
        Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(DUPLICATE);
        assertError(stylesheet, QLSChecker.DUPLICATE_REFERENCE_TO_FORM_EXPRESSION);
    }

    @Test
    public void testInvalidWidgetAssignment() {
        Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(INVALID_WIDGET);
        assertError(stylesheet, QLSChecker.CAN_NOT_USE_WIDGET);
    }

    @Test
    public void testInvalidDefaultStyle() {
        Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(INVALID_DEFAULT);
        assertError(stylesheet, QLSChecker.CAN_NOT_USE_WIDGET);
    }

    private void assertError(Stylesheet stylesheet, String error) {
        List<String> errors = checker.checkForErrors(form, stylesheet);
        assertEquals(1, errors.size());
        assert (errors.get(0).startsWith(error));
    }
}
