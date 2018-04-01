package ql.analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.QLTestUtilities;

public class InvalidDuplicateQuestionTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void validDuplicateQuestion() throws Exception {
        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ql/DuplicateQuestions/ValidDuplicateQuestion.ql"));
    }

    @Test
    public void invalidDuplicateQuestion() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Redeclaration of question(s) with different type");

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ql/DuplicateQuestions/InvalidDuplicateQuestion.ql"));
    }
}
