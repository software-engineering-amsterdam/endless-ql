package tests.QLS;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import QL.classes.values.DecimalValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.MoneyValue;
import QL.classes.values.StringValue;
import QL.parsing.TreeBuilder;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.FormVisitor;
import QLS.classes.Page;
import QLS.classes.Stylesheet;
import QLS.classes.blocks.Section;
import QLS.parsing.gen.QLSParser;
import QLS.parsing.visitors.StylesheetVisitor;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QLSGrammarTest {

    private StylesheetVisitor getStyleSheet(String qlsFilePath) {
        try {
            FileInputStream qlsInputStream = new FileInputStream(qlsFilePath);
            QLSParser.StylesheetContext stylesheetContext = new TreeBuilder().buildQls(qlsInputStream);
            StylesheetVisitor stylesheetVisitor = new StylesheetVisitor(null);
            stylesheetVisitor.visitStylesheet(stylesheetContext);
            return stylesheetVisitor;
        } catch (IOException e) {
            throw new RuntimeException("Could not find QLS file");
        }
    }

    @Test
    public void testStylesheet() {
        Stylesheet stylesheet = getStyleSheet("src/resources/QLS/exampleForm5.qls").getStylesheet();
    }

    @Test
    public void testPages() {
        List<Page> pageList = new ArrayList<Page>(getStyleSheet("src/resources/QLS/exampleForm5.qls").getPages().values());
    }

    @Test
    public void testSections() {
        List<Section> sectionList =  new ArrayList<Section>(getStyleSheet("src/resources/QLS/exampleForm5.qls").getSections().values());


    }


//    @Test
//    public void testPages() {
//        List<Pages> questionList = getQuestions("src/resources/QL/formQl.ql");
//
//        //amount of questions
//        assertEquals(questionList.size(), 6);
//
//        //first question, id + text
//        assertEquals(questionList.get(0).getId(), "hasSoldHouse");
//        assertEquals(questionList.get(0).getText(), "\"Did you sell a house in 2010?\"");
//
//        //last question, id + text
//        assertEquals(questionList.get(questionList.size() - 1).getId(), "valueResidue");
//        assertEquals(questionList.get(questionList.size() - 1).getText(), "\"Value residue:\"");
//
//        //fixed questions
//        assertTrue("This question will be fixed", questionList.get(questionList.size() - 1).isFixed());
//        assertFalse("This question won't be fixed", questionList.get(0).isFixed());
//    }

}