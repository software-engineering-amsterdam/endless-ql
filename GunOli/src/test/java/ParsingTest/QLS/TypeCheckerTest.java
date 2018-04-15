package ParsingTest.QLS;

import GUI.Parser;
import QL.AST.Form;
import QLS.Analysis.QLSTypeChecker;
import QLS.AST.Stylesheet;
import org.junit.Test;

public class TypeCheckerTest {

    private String exampleQL = "./src/test/resources/OfficialExample/example.ql";
    private String exampleQLS = "./src/test/resources/OfficialExample/example.qls";
    private String exampleQLS2 = "./src/test/resources/FailingForm/WrongTypes/QLSDuplicateQuestions.qls";
    private String exampleQLS3 = "./src/test/resources/FailingForm/WrongTypes/QLSUnplacedQuestion.qls";
    private String exampleQLS4 = "./src/test/resources/FailingForm/WrongTypes/QLSUnkownRefrence.qls";





    public void TypeCheckerTest(String qls, String ql){
        Stylesheet parsedQLS = new Parser().parseInputToStyleSheet(qls);
        Form parsedQL = new Parser().parseInputToForm(ql);
        QLSTypeChecker tc = new QLSTypeChecker(parsedQLS, parsedQL);
        tc.typeCheck();
        tc.detectUnknownReferences();
        tc.detectUnplacedQuestions();
        tc.detectDuplicateQuestions();
    }

    @Test
    public void shouldNotThrowException(){
        try{
            TypeCheckerTest(exampleQLS, exampleQL);
            assert true;
        }
        catch (IllegalArgumentException e){
            assert false;
        }
    }
    @Test
    public void shouldThrowExceptionDuplicate(){
        try{
            TypeCheckerTest(exampleQLS2, exampleQL);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }
    @Test
    public void shouldThrowExceptionUnplaced(){
        try{
            TypeCheckerTest(exampleQLS3, exampleQL);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }
    @Test
    public void shouldThrowExceptionUnknown(){
        try{
            TypeCheckerTest(exampleQLS4, exampleQL);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }
}
