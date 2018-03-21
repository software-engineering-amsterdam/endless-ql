package qls.analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import qls.QLSTestUtilities;

public class QuestionAnalyzerTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void invalidUnplacedFields() throws Exception {
        String[] expectedStrings = {"Unplaced questions by QLS: ", "someInteger", "someBoolean"};

        expectedEx.expect(IllegalArgumentException.class);
        for (String expectedString : expectedStrings) {
            expectedEx.expectMessage(expectedString);
        }
        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/InvalidStylesheets/SimpleFormUnplacedFields.qls");
    }

    @Test
    public void invalidMultiplePlacedFields() throws Exception {
        String[] expectedStrings = {"Question(s) referenced more than once: ", "someDecimal", "someDate"};

        expectedEx.expect(IllegalArgumentException.class);
        for (String expectedString : expectedStrings) {
            expectedEx.expectMessage(expectedString);
        }
        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/InvalidStylesheets/SimpleFormMultiplePlacedFields.qls");
    }

    @Test
    public void unknownQuestion() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unknown reference(s) to questions: [unknownQuestion]");

        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/InvalidStylesheets/SimpleFormUnknownQuestion.qls");
    }
}
