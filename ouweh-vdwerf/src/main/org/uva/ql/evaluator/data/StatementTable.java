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
    private final Map<String, Conditional> conditions;

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

    public void addConditional(String name, Conditional conditional) {
        conditions.put(name, conditional);
    }

    public boolean questionIsConditional(String questionName) {
        return conditions.containsKey(questionName);
    }

    public Conditional getConditionByQuestionID(String questionName) {
        return conditions.get(questionName);
    }

}
