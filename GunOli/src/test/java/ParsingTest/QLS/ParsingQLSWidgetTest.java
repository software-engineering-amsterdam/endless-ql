package ParsingTest.QLS;

import GUI.Parser;
import QLS.AST.Statements.QLSQuestion;
import QLS.AST.Stylesheet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ParsingQLSWidgetTest {

    private String file = "./src/test/resources/OfficialExample/example.qls";

    public ArrayList ParsingQLS(String file){

        Stylesheet parsed = new Parser().parseInputToStyleSheet(file);
        ArrayList Widgets = new ArrayList();
        for(QLSQuestion question : parsed.getQlsQuestions()){
            Widgets.add(question.getWidget());
        }
        return Widgets;
    }

    @Test
    public void RunTest(){

        ArrayList toTest = new ArrayList();
        toTest.add("CheckBox");
        toTest.add("null");
        toTest.add("SpinBox");
        toTest.add("SpinBox");
        toTest.add("null");
        toTest.add("Radio");

        assertEquals(toTest.toString(), ParsingQLS(file).toString());
    }
}
