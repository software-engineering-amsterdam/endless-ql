package model;

import expression.Expression;
import expression.ExpressionVariableUndefined;

import java.util.HashMap;
import java.util.Map;

// TODO: do this differently. also: lookup table per scope?
public class LookupTable {
    private static LookupTable instance = null;

    private Map<String, Question> table;

    private LookupTable() {
        table = new HashMap<>();
    }

    public static LookupTable getInstance() {
        if (instance == null) {
            instance = new LookupTable();
        }
        return instance;
    }

    public Expression getQuestionAnswer(String identifier) {
        if (table.containsKey(identifier)) {
            // TODO: switch to getter-setter?
            return table.get(identifier).answer;
        }

        return new ExpressionVariableUndefined();
    }

    public void insert(Question question) {
        if (table.containsKey(question.name)) {
            // TODO: errors with line numbers
            throw new IllegalArgumentException("Identifier already exists");
        }
        table.put(question.name, question);


        LookupTable.getInstance().getQuestionAnswer("");
    }

}
