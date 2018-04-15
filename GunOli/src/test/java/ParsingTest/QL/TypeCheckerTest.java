package ParsingTest.QL;
import QL.AST.Form;
import QL.Analysis.QLTypeChecker;
import QL.Evaluation.ExpressionTable;
import org.junit.Test;
import GUI.Parser;

public class TypeCheckerTest {

    private String integer = "./src/test/resources/FailingForm/WrongTypes/Integer.ql";
    private String decimal = "./src/test/resources/FailingForm/WrongTypes/Decimal.ql";
    private String booleanql = "./src/test/resources/FailingForm/WrongTypes/Boolean.ql";
    private String string = "./src/test/resources/FailingForm/WrongTypes/String.ql";
    private String money = "./src/test/resources/FailingForm/WrongTypes/Money.ql";

    public void TypeCheckerTest(String file){
        Form parsed = new Parser().parseInputToForm(file);
        ExpressionTable expTable = new ExpressionTable(parsed);
        QLTypeChecker tc = new QLTypeChecker(parsed, expTable);
        tc.typeCheck();
    }

    @Test
    public void shouldThrowExceptionInteger(){
        try{
            TypeCheckerTest(integer);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }
    @Test
    public void shouldThrowExceptionDecimal(){
        try{
            TypeCheckerTest(decimal);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }

    @Test
    public void shouldThrowExceptionBoolean(){
        try{
            TypeCheckerTest(booleanql);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }
    @Test
    public void shouldThrowExceptionString(){
        try{
            TypeCheckerTest(string);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }
    @Test
    public void shouldThrowExceptionMoney(){
        try{
            TypeCheckerTest(money);
            assert false;
        }
        catch (IllegalArgumentException e){
            assert true;
        }
    }

}
