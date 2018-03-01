import model.Form;
import model.Question;

public class TypeChecker {
    private Form form;

    TypeChecker(Form form) {
        this.form = form;
    }

    public void typeCheck() {
        for(Question question : form.questions) {
            System.out.println(question.condition);
            question.condition.typeCheck();
            question.answer.typeCheck();
        }
    }


}
