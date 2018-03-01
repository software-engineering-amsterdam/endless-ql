import model.Form;
import model.Question;

public class TypeChecker {
    private Form form;

    TypeChecker(Form form) {
        this.form = form;
    }

    public void typeCheck() {
        for(Question question : form.questions) {
            // Type check condition and answer expression
            question.condition.typeCheck();
            question.answer.typeCheck();

            // Type check question default value assignment
            question.typeCheck();
        }
    }


}
