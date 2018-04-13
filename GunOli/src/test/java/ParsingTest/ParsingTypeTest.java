package ParsingTest;

import GUI.Parser;
import QL.AST.Form;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ParsingTypeTest {

    private String example1 = "./src/test/resources/PassingForms/example1.ql";
    private String example2 = "./src/test/resources/PassingForms/example2.ql";

    public ArrayList typeList(String file){
        ArrayList questions;
        ArrayList types = new ArrayList();
        Form parsed = new Parser().parseInputToForm(file);

        questions = parsed.getQuestions();
        for (int i = 0; i < questions.size(); i++){
            types.add(parsed.getQuestions().get(i).getType());
        }
        return types;
    }

    @Test
    public void example1TypeTest(){
        ArrayList example1Expected = new ArrayList();
        example1Expected.add("Boolean");
        example1Expected.add("Boolean");
        example1Expected.add("Boolean");
        example1Expected.add("String");
        example1Expected.add("String");
        example1Expected.add("Integer");
        example1Expected.add("Decimal");
        example1Expected.add("Money");
        example1Expected.add("Money");
        example1Expected.add("Money");
        example1Expected.add("Boolean");
        example1Expected.add("Date");

        System.out.println(typeList(example1));
        assertEquals(example1Expected, typeList(example1));
    }

}
