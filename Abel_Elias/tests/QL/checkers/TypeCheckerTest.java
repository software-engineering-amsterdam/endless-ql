package tests.QL.checkers;

import QL.parsing.TreeBuilder;
import QL.parsing.checkers.CyclicChecker;
import QL.parsing.checkers.TypeChecker;
import QL.parsing.checkers.errors.CyclicError;
import QL.parsing.checkers.errors.TypeMismatchError;
import QL.parsing.gen.QLParser;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class TypeCheckerTest extends CheckerTest {

    private TypeChecker typeChecker;

    @Before
    public void init() {
        this.typeChecker = new TypeChecker();
    }

    @Test(expected = TypeMismatchError.class)
    public void issueTypeMismatchErrorCondition() {
        QLParser.FormContext formContext = getFormContext("src/resources/QL/tests/typeTests/typesFormCondition.ql");
        typeChecker.checkForm(formContext);
    }

    @Test(expected = TypeMismatchError.class)
    public void issueTypeMismatchErrorAction() {
        QLParser.FormContext formContext = getFormContext("src/resources/QL/tests/typeTests/typesFormAction.ql");
        typeChecker.checkForm(formContext);
    }

    @Test(expected = Test.None.class)
    public void issueNoErrors() {
        QLParser.FormContext formContext = getFormContext("src/resources/QL/formQl.ql");
        typeChecker.checkForm(formContext);
    }
}
