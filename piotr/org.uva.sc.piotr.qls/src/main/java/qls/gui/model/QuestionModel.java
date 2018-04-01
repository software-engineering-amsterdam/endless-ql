package qls.gui.model;

import ql.ast.model.expressions.Expression;
import ql.ast.model.statements.Question;

public class QuestionModel extends ql.gui.model.QuestionModel {
    public QuestionModel(Question question, Expression visibilityCondition) {
        super(question, visibilityCondition);
    }
}
