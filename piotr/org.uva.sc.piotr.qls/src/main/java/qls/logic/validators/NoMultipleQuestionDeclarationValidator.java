package qls.logic.validators;

import ql.error.Error;
import ql.logic.validators.Validator;
import qls.ast.model.Stylesheet;
import qls.logic.collectors.QLSCollectQuestionsVisitor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoMultipleQuestionDeclarationValidator extends Validator {

    private Stylesheet stylesheet;

    public NoMultipleQuestionDeclarationValidator(Stylesheet stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public boolean validate() {
        QLSCollectQuestionsVisitor QLSVisitor = new QLSCollectQuestionsVisitor();
        List<String> stylesheetQuestionsList = QLSVisitor.getQuestionsNames(stylesheet);
        Set<String> stylesheetQuestionsSet = new HashSet<>(stylesheetQuestionsList);

        if (stylesheetQuestionsSet.size() != stylesheetQuestionsList.size()) {
            String message = "There are questions declared multiple times in the stylesheet.";
            this.setError(new Error(Error.Level.CRITICAL, message));
            return false;
        }

        return true;
    }

}
