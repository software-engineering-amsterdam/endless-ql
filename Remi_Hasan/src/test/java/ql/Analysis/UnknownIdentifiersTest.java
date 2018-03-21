package ql.Analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.QLTestUtilities;

public class UnknownIdentifiersTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void unknownAssignment() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unknown reference(s) to identifiers: [unknownString]");

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/QL/UnknownIdentifiers/UnknownAssignment.ql"));
    }

    @Test
    public void unknownCondition() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unknown reference(s) to identifiers: [unknownBoolean]");
        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/QL/UnknownIdentifiers/UnknownCondition.ql"));
    }

    @Test
    public void unknownExpressionAssignment() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unknown reference(s) to identifiers: [unknownInteger]");
        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/QL/UnknownIdentifiers/UnknownExpressionAssignment.ql"));
    }

    @Test
    public void unknownExpressionCondition() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unknown reference(s) to identifiers: [unknownDecimal]");
        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/QL/UnknownIdentifiers/UnknownExpressionCondition.ql"));
    }
}
