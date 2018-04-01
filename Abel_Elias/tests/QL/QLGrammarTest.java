package tests.QL;

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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public class QLGrammarTest {

    private List<Question> getQuestions(String qlFilePath) {
        try {
            FileInputStream qlInputStream = new FileInputStream(qlFilePath);
            QLParser.FormContext form = new TreeBuilder().build(qlInputStream);
            FormVisitor formVisitor = new FormVisitor().visitForm(form);
            return new ArrayList<>(formVisitor.getQuestions().values());
        } catch (IOException e) {
            throw new RuntimeException("Could not find QL file");
        }
    }

    @Test
    public void testQuestions() {
        List<Question> questionList = getQuestions("src/resources/QL/formQl.ql");

        //amount of questions
        assertEquals(questionList.size(), 6);

        //first question, id + text
        assertEquals(questionList.get(0).getId(), "hasSoldHouse");
        assertEquals(questionList.get(0).getText(), "\"Did you sell a house in 2010?\"");

        //last question, id + text
        assertEquals(questionList.get(questionList.size() - 1).getId(), "valueResidue");
        assertEquals(questionList.get(questionList.size() - 1).getText(), "\"Value residue:\"");

        //fixed questions
        assertTrue("This question will be fixed", questionList.get(questionList.size() - 1).isFixed());
        assertFalse("This question won't be fixed", questionList.get(0).isFixed());
    }

    @Test
    public void testQuestionTypes() {
        List<Question> questionList = getQuestions("src/resources/QL/tests/typesForm.ql");

        //Test for each value
        assertTrue(questionList.get(0).getValue() instanceof BooleanValue);
        assertTrue(questionList.get(1).getValue() instanceof IntegerValue);
        assertTrue(questionList.get(2).getValue() instanceof StringValue);
        assertTrue(questionList.get(3).getValue() instanceof MoneyValue);
        assertTrue(questionList.get(4).getValue() instanceof DateValue);
        assertTrue(questionList.get(5).getValue() instanceof DecimalValue);

    }
}