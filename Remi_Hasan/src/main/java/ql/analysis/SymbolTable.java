package ql.analysis;

import javafx.util.Pair;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableUndefined;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    private Map<String, Expression> table;
    private List<ChangeListener> listeners;

    public SymbolTable() {
        this.table = new HashMap<>();
        this.listeners = new ArrayList<>();
    }

    public void buildTable(Form form) {
        for (Question question : form.questions) {
            if(question.isComputed()) {
                table.put(question.identifier, question.computedAnswer);
            } else {
                table.put(question.identifier, new ExpressionVariableUndefined(question.getToken(), question.type));
            }
        }
    }

    public Expression getExpression(String identifier) {
        if (this.table.containsKey(identifier)) {
            return this.table.get(identifier);
        } else {
            throw new UnsupportedOperationException("Cannot get value for unknown field '" + identifier + "'.");
        }
    }

    public void addListener(ChangeListener listener){
        this.listeners.add(listener);
    }

    public void setExpression(String identifier, Expression value) {
        this.table.put(identifier, value);

        // Notify listener
        for(ChangeListener listener : listeners){
            listener.stateChanged(new ChangeEvent(new Pair(identifier, value)));
        }


        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this);
        System.out.println("\n\n");
        for(Map.Entry<String, Expression> entry : table.entrySet()){
            Value evaluatedValue = expressionEvaluator.visit(entry.getValue());
            System.out.println(entry.getKey() + " " + evaluatedValue.toString());
        }
    }
}
