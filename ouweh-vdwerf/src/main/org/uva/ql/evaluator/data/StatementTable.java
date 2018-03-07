package org.uva.ql.evaluator.data;


import org.uva.ql.ast.Question;
import org.uva.ql.ast.Conditional;
import org.uva.ql.ast.expression.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

public class StatementTable {

    private final Map<String, Question> questions;
    private final Map<String, Expression> conditions;

    public StatementTable() {
        questions = new LinkedHashMap<>();
        conditions = new HashMap<>();
    }

    public void addQuestion(String name, Question question) {
        questions.put(name, question);
    }

    public List<Question> getQuestionsAsList() {
        return new ArrayList<>(questions.values());
    }

    public void addConditional(String name, Expression expression) {
        conditions.put(name, expression);
    }

    public boolean questionIsConditional(String questionName) {
        return conditions.containsKey(questionName);
    }

    public Expression getConditionByQuestionID(String questionName) {
        return conditions.get(questionName);
    }

}
