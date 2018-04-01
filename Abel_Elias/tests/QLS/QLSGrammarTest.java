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
import QLS.classes.blocks.StyledQuestion;
import QLS.parsing.gen.QLSParser;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.widgets.CheckBoxWidget;
import gui.widgets.DropDownWidget;
import gui.widgets.SpinBoxWidget;
import gui.widgets.TextWidget;
import javafx.scene.control.Slider;
import org.junit.Before;
import org.junit.Test;

import java.awt.dnd.DropTargetDragEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QLSGrammarTest {

    private LinkedHashMap<String, Question> questionLinkedHashMap;
    @Before
    public void init() {
        questionLinkedHashMap = new LinkedHashMap<>();
        questionLinkedHashMap.put("id", new Question("id", "text", new BooleanValue(true), false));
    }

    private StylesheetVisitor getStyleSheet(String qlsFilePath) {
        try {
            FileInputStream qlsInputStream = new FileInputStream(qlsFilePath);
            QLSParser.StylesheetContext stylesheetContext = new TreeBuilder().buildQls(qlsInputStream);
            StylesheetVisitor stylesheetVisitor = new StylesheetVisitor(questionLinkedHashMap);
            stylesheetVisitor.visitStylesheet(stylesheetContext);
            return stylesheetVisitor;
        } catch (IOException e) {
            throw new RuntimeException("Could not find QLS file");
        }
    }

    @Test
    public void testStylesheet() {
        Stylesheet stylesheet = getStyleSheet("resources/QLS/miscellaneous/exampleForm5.qls").getStylesheet();

        //name of stylesheet
        assertEquals(stylesheet.getId(), "taxOfficeExample");

        //amount of pages
        assertEquals(stylesheet.getPages().size(), 2);
    }

    @Test
    public void testPages() {
        List<Page> pageList = new ArrayList<Page>(getStyleSheet("src/resources/QLS/tests/pagesSheet.qls").getPages().values());

        //amount of pages
        assertEquals(pageList.size(), 4);

        //first page, id
        assertEquals(pageList.get(0).getId(), "firstPage");

        //last page, id
        assertEquals(pageList.get(pageList.size() - 1).getId(), "fourthPage");

        //Section within Page, name
        assertEquals(pageList.get(2).getSections().get(0).getName(), "\"section2\"");
    }

    @Test
    public void testSections() {
        List<Section> sectionList =  new ArrayList<Section>(
                getStyleSheet("src/resources/QLS/tests/sectionsSheet.qls").getSections().values());

        //amount of sections
        assertEquals(sectionList.size(), 10);

        //first section, name
        assertEquals(sectionList.get(0).getName(), "\"section1\"");

        //last section, name
        assertEquals(sectionList.get(sectionList.size() - 1).getName(), "\"section5\"");

        //nested page level 1, name
        assertEquals(sectionList.get(1).getName(), "\"section2.1\"");
        //nested page level 2, name
        assertEquals(sectionList.get(4).getName(), "\"section3.1.1\"");
        //nested page level 3, name
        assertEquals(sectionList.get(3).getName(), "\"section3.1.1.1\"");

    }

    @Test
    public void testStyledQuestions() {
        List<StyledQuestion> styledQuestionList =  new ArrayList<StyledQuestion>(
                getStyleSheet("src/resources/QLS/tests/styledQuestionsSheet.qls").getQuestions().values());

        //amount of sections
        assertEquals(styledQuestionList.size(), 3);

        //first section, name
        assertEquals(styledQuestionList.get(0).getName(), "styledQuestion1");

        //last section, name
        assertEquals(styledQuestionList.get(styledQuestionList.size() - 1).getName(), "styledQuestion3");

        //parent, id
        assertEquals(styledQuestionList.get(1).getParentId(), "\"section2\"");

    }

    @Test
    public void testWidgetTypes() {
        List<StyledQuestion> styledQuestionList =  new ArrayList<StyledQuestion>(
                getStyleSheet("src/resources/QLS/tests/styledQuestionTypes.qls").getQuestions().values());

        //Test for each value
        assertTrue(styledQuestionList.get(0).getWidget() instanceof CheckBoxWidget);
//        assertTrue(styledQuestionList.get(1).getWidget() instanceof );
        assertTrue(styledQuestionList.get(2).getWidget() instanceof SpinBoxWidget);
        assertTrue(styledQuestionList.get(3).getWidget() instanceof DropDownWidget);
        assertTrue(styledQuestionList.get(4).getWidget() instanceof TextWidget);
//        assertTrue(styledQuestionList.get(5).getWidget().getWidgetType() instanceof SliderWidget);

    }
}