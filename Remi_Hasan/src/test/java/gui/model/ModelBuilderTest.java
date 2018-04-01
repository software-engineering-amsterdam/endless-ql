package gui.model;

import gui.builder.GUIFormBuilder;
import org.junit.Test;
import ql.QLForm;
import qls.QLSBuilder;
import qls.model.StyleSheet;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ModelBuilderTest {

    @Test
    public void buildQLGUIModel() throws Exception {
        QLForm qlForm = new QLForm(ModelBuilderTest.class
                .getResourceAsStream("/ql/ValidForms/ConditionFormTrue.ql"));

        GUIFormBuilder guiFormBuilder = new GUIFormBuilder();
        GUIForm guiForm = guiFormBuilder.buildQLForm(qlForm.getForm());

        List<GUIQuestion> questions = guiForm.getQuestions();

        assertEquals(3, questions.size());

        // Check if calculated answer was set correctly
        assertEquals(Integer.valueOf(2), qlForm.evaluateExpression(questions.get(0).getComputedAnswer()).getIntegerValue());

        // Check if conditions were set correctly
        assertEquals(Boolean.valueOf(true), qlForm.evaluateExpression(questions.get(1).getCondition()).getBooleanValue());
        assertEquals(Boolean.valueOf(false), qlForm.evaluateExpression(questions.get(2).getCondition()).getBooleanValue());
    }

    @Test
    public void buildQLSGUIModel() throws Exception {
        QLForm qlForm = new QLForm(ModelBuilderTest.class
                .getResourceAsStream("/ql/ValidForms/SimpleForm.ql"));

        QLSBuilder qlsBuilder = new QLSBuilder(qlForm.getForm());
        StyleSheet styleSheet = qlsBuilder.parseStyleSheet(ModelBuilderTest.class
                .getResourceAsStream("/qls/ValidStylesheets/SimpleForm.qls"));

        GUIFormBuilder guiFormBuilder = new GUIFormBuilder();
        GUIFormWithStyling guiForm = guiFormBuilder.buildQLSForm(qlForm.getForm(), styleSheet);

        assertEquals(guiForm.getQuestions().size(), 6);
        assertEquals(guiForm.getPages().size(), 2);
        assertEquals(guiForm.getPages().get(0).getIdentifier(), "Numbers");
    }
}
