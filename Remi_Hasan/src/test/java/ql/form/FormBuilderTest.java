package ql.form;

import org.junit.Test;
import ql.QLForm;
import ql.QLTestUtilities;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.expression.ReturnType;
import ql.model.statement.Question;
import ql.visitor.QLVisitor;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FormBuilderTest {

    private List<Question> getQuestions(Form form) {
        List<Question> questions = new ArrayList<>();

        form.accept(new QLVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                questions.add(question);
                return super.visit(question);
            }
        });

        return questions;
    }

    @Test
    public void simpleForm() throws Exception {
        Form form = QLTestUtilities.buildForm(FormBuilderTest.class
                .getResourceAsStream("/ql/ValidForms/SimpleForm.ql"));

        List<Question> questions = this.getQuestions(form);

        assertEquals(form.getIdentifier(), "simpleForm");
        assertEquals(questions.size(), 6);
        assertEquals(questions.get(0).getIdentifier(), "someInteger");
        assertEquals(questions.get(0).getLabel(), "Can you give me an integer value?");
        assertEquals(questions.get(0).getType(), ReturnType.INTEGER);
    }

    @Test
    public void conditionFalseForm() throws Exception {
        Form form = QLTestUtilities.buildForm(FormBuilderTest.class
                .getResourceAsStream("/ql/ValidForms/ConditionFormFalse.ql"));
        List<Question> questions = this.getQuestions(form);
        assertEquals(questions.size(), 3);
    }

    @Test
    public void computedForm() throws Exception {
        QLForm qlForm = new QLForm(FormBuilderTest.class
                .getResourceAsStream("/ql/ValidForms/ComputedForm.ql"));

        Form form = qlForm.getForm();

        List<Question> questions = this.getQuestions(form);

        // Test whether computed field is computed correctly based on another field value
        Value staticResult = qlForm.evaluateExpression(questions.get(0).getComputedAnswer());
        assertEquals(staticResult.getIntegerValue(), Integer.valueOf(2));

        Value calculationResult = qlForm.evaluateExpression(questions.get(1).getComputedAnswer());
        assertEquals(calculationResult.getIntegerValue(), Integer.valueOf(5));
    }

}
