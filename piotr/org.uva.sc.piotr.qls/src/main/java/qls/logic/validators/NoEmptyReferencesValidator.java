package qls.logic.validators;

import ql.ast.model.Form;
import ql.ast.model.statements.Question;
import ql.error.Error;
import ql.logic.collectors.CollectQuestionsVisitor;
import ql.logic.validators.Validator;
import qls.ast.model.Stylesheet;
import qls.logic.collectors.QLSCollectQuestionsVisitor;

import java.util.List;

public class NoEmptyReferencesValidator extends Validator {

    private Form form;
    private Stylesheet stylesheet;

    public NoEmptyReferencesValidator(Form form, Stylesheet stylesheet) {
        this.form = form;
        this.stylesheet = stylesheet;
    }

    @Override
    public boolean validate() {
        CollectQuestionsVisitor QLVisitor = new CollectQuestionsVisitor();
        List<Question> formQuestions = QLVisitor.getQuestions(form);

        QLSCollectQuestionsVisitor QLSVisitor = new QLSCollectQuestionsVisitor();
        List<String> stylesheetQuestionsNames = QLSVisitor.getQuestionsNames(stylesheet);

        for (String qlsQuestionName : stylesheetQuestionsNames) {
            boolean found = false;
            for (Question qlQuestion : formQuestions) {
                if (qlQuestion.getVariableName().equals(qlsQuestionName))
                    found = true;
            }
            if (!found) {
                String message = "Question \"" + qlsQuestionName+"\" declared in the stylesheet refers to a non-existing question in the QL file.";
                this.setError(new Error(Error.Level.CRITICAL, message));
                return false;
            }
        }
        return true;
    }

}
