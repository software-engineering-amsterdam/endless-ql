package ParsingTest.QLS;

import QLS.AST.Stylesheet;
import org.junit.Test;
import GUI.Parser;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ParsingQLSPagesTest {

    private String exampleQLS = "./src/test/resources/OfficialExample/example.qls";

    public ArrayList ParsingQLSPages(String file){

        Stylesheet parsed = new Parser().parseInputToStyleSheet(file);
        ArrayList pages = new ArrayList();

        for (int i = 0; i < parsed.getPages().size(); i++){
            pages.add(parsed.getPages().get(i).getIdentifier());
        }


        return pages;
    }

    @Test
    public void QLSPageTest(){
        ArrayList toTest = new ArrayList();
        toTest.add("Housing");
        toTest.add("Selling");

       assertEquals(toTest, ParsingQLSPages(exampleQLS));
    }


}
