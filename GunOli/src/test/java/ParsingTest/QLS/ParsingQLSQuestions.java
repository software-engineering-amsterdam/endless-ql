package ParsingTest.QLS;

import GUI.Parser;
import QLS.AST.Stylesheet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ParsingQLSQuestions {
    private String exampleQLS = "./src/test/resources/OfficialExample/example.qls";

    public ArrayList ParsingQLSQuestions(String file){

        Stylesheet parsed = new Parser().parseInputToStyleSheet(file);
        ArrayList Questions = new ArrayList();

        for (int i = 0; i < parsed.getQlsQuestions().size(); i++){
            Questions.add(parsed.getQlsQuestions().get(i).getIdentifier());
        }
        return Questions;
    }
    @Test
    public void QLSQuestionTest(){
        ArrayList toTest = new ArrayList();
        toTest.add("hasBoughtHouse");
        toTest.add("hasMaintLoan");
        toTest.add("sellingPrice");
        toTest.add("privateDebt");
        toTest.add("valueResidue");
        toTest.add("hasSoldHouse");

        assertEquals(toTest, ParsingQLSQuestions(exampleQLS));
    }



}
