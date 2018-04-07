package qls.logic.validators;

import ql.ast.model.Form;
import ql.ast.model.statements.Question;
import ql.error.Error;
import ql.logic.collectors.CollectQuestionsVisitor;
import ql.logic.validators.Validator;
import qls.ast.model.Stylesheet;
import qls.logic.collectors.QLSCollectQuestionsVisitor;

import java.util.List;

public class AllQuestionsCoveredValidator extends Validator {

    private Form form;
    private Stylesheet stylesheet;

    public AllQuestionsCoveredValidator(Form form, Stylesheet stylesheet) {
        this.form = form;
        this.stylesheet = stylesheet;
    }

    @Override
    public boolean validate() {
        CollectQuestionsVisitor QLVisitor = new CollectQuestionsVisitor();
        List<Question> formQuestions = QLVisitor.getQuestions(form);

        QLSCollectQuestionsVisitor QLSVisitor = new QLSCollectQuestionsVisitor();
        List<String> stylesheetQuestionsNames = QLSVisitor.getQuestionsNames(stylesheet);

        for (Question qlQuestion : formQuestions) {
            boolean found = false;
            for (String qlsQuestionName : stylesheetQuestionsNames) {
                if (qlQuestion.getVariableName().equals(qlsQuestionName))
                    found = true;
            }
            if (!found) {
                String message = "Question \"" + qlQuestion.getVariableName() + "\" found in the QL file in line " + qlQuestion.getMetaInformation().getStartLine() + ", but not fond in the QLS file.";
                this.setError(new Error(Error.Level.CRITICAL, message));
                return false;
            }
        }
        return true;
    }

}
