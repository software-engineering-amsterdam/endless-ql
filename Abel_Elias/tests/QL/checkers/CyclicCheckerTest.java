package tests.QL.checkers;

import QL.classes.Question;
import QL.parsing.TreeBuilder;
import QL.parsing.checkers.CyclicChecker;
import QL.parsing.checkers.errors.CyclicError;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.FormVisitor;
import org.junit.Before;
import org.junit.Test;

public class CyclicCheckerTest extends CheckerTest{

    private CyclicChecker cyclicChecker;

    @Before
    public void init() {
        this.cyclicChecker = new CyclicChecker();
    }

    @Test(expected = CyclicError.class)
    public void issueCyclicErrorAcrossQuestions() {
        QLParser.FormContext formContext = getFormContext("resources/QL/tests/cyclicTests/cyclicFormAcross.ql");
        cyclicChecker.checkForm(formContext);
    }

    @Test(expected = CyclicError.class)
    public void issueCyclicErrorInQuestion() {
        QLParser.FormContext formContext = getFormContext("resources/QL/tests/cyclicTests/cyclicFormItself.ql");
        cyclicChecker.checkForm(formContext);
    }

    @Test(expected = Test.None.class)
    public void issueNoErrors() {
        QLParser.FormContext formContext = getFormContext("src/resources/QL/formQl.ql");
        cyclicChecker.checkForm(formContext);
    }
}
