package tests.QL.checkers;

import QL.parsing.checkers.VariableChecker;
import QL.parsing.checkers.errors.DuplicateVarError;
import QL.parsing.checkers.errors.UndeclaredVarError;
import QL.parsing.gen.QLParser;
import org.junit.Before;
import org.junit.Test;

public class VariableCheckerTest extends CheckerTest {
    private VariableChecker variableChecker;

    @Before
    public void init() {
        this.variableChecker = new VariableChecker();
    }

    @Test(expected = DuplicateVarError.class)
    public void issueDuplicateVarError() {
        QLParser.FormContext formContext = getFormContext("src/resources/QL/tests/variableTests/duplicatedVariables.ql");
        variableChecker.checkForm(formContext);
    }

    @Test(expected = UndeclaredVarError.class)
    public void issueUndeclaredVarError() {
        QLParser.FormContext formContext = getFormContext("src/resources/QL/tests/variableTests/undeclaredVariables.ql");
        variableChecker.checkForm(formContext);
    }

    @Test(expected = Test.None.class)
    public void issueNoErrors() {
        QLParser.FormContext formContext = getFormContext("src/resources/QL/tests/variableTests/acceptableForm.ql");
        variableChecker.checkForm(formContext);
    }
}
