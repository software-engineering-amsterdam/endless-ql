package ql.form;

import org.junit.Test;
import ql.QLBaseVisitor;
import ql.QLFormBuilder;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.ReturnType;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FormBuilderTest {

    private List<Question> getQuestions(Form form) {
        List<Question> questions = new ArrayList<>();

        form.accept(new QLBaseVisitor<Void>() {
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
        QLFormBuilder formBuilder = new QLFormBuilder();
        Form form = formBuilder.buildForm(FormBuilderTest.class
                .getResourceAsStream("/ql/ValidForms/SimpleForm.ql"));
        List<Question> questions = this.getQuestions(form);

        assertEquals(form.identifier, "simpleForm");
        assertEquals(questions.size(), 6);
        assertEquals(questions.get(0).identifier, "someInteger");
        assertEquals(questions.get(0).label, "Can you give me an integer value?");
        assertEquals(questions.get(0).type, ReturnType.INTEGER);
    }

    @Test
    public void conditionFalseForm() throws Exception {
        QLFormBuilder formBuilder = new QLFormBuilder();
        Form form = formBuilder.buildForm(FormBuilderTest.class
                .getResourceAsStream("/ql/ValidForms/ConditionFormFalse.ql"));
        List<Question> questions = this.getQuestions(form);
        assertEquals(questions.size(), 3);
    }

    @Test
    public void computedForm() throws Exception {
        QLFormBuilder formBuilder = new QLFormBuilder();
        Form form = formBuilder.buildForm(FormBuilderTest.class.
                getResourceAsStream("/ql/ValidForms/ComputedForm.ql"));
        List<Question> questions = this.getQuestions(form);

        // Test whether computed field is computed correctly based on another field value
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(formBuilder.getSymbolTable());

        Value staticResult = expressionEvaluator.visit(questions.get(0).computedAnswer);
        assertEquals(staticResult.getIntValue(), Integer.valueOf(2));

        Value calculationResult = expressionEvaluator.visit(questions.get(1).computedAnswer);
        assertEquals(calculationResult.getIntValue(), Integer.valueOf(5));
    }

}
