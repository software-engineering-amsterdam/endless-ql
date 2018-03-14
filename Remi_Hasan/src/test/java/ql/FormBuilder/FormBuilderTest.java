package ql.FormBuilder;

import org.junit.Test;
import ql.Analysis.UnknownIdentifiersTest;
import ql.QLTestUtilities;
import ql.model.Form;

import static junit.framework.TestCase.assertEquals;

public class FormBuilderTest {

    @Test
    public void simpleForm() throws Exception {
        Form form = QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ValidForms/SimpleForm.ql"));

        assertEquals(form.questions.size(), 6);
    }

    @Test
    public void conditionForm() throws Exception {
        Form form = QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ValidForms/ConditionForm.ql"));

        assertEquals(form.questions.size(), 7);
    }

}
