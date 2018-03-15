package ql.analysis;

import javafx.util.Pair;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionVariable;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableUndefined;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.format.DateTimeFormatter;
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
                table.put(question.name, question.computedAnswer);
            } else {
                table.put(question.name, new ExpressionVariableUndefined(question.getToken(), question.type));
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

    // TODO: move to value?
    public String getStringValue(String identifier, ReturnType type) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this);
        Value evaluated = expressionEvaluator.visit(table.get(identifier));

        // Undefined values should display nothing
        if (evaluated.isUndefined()) {
            return "";
        }

        switch (type) {
            case INTEGER:
                return evaluated.getIntValue().toString();
            case DECIMAL:
                return evaluated.getDecimalValue().toString();
            case MONEY:
                return evaluated.getMoneyValue().toString();
            case STRING:
                return evaluated.getStringValue();
            case BOOLEAN:
                return evaluated.getBooleanValue().toString();
            case DATE:
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return evaluated.getDateValue().format(dateFormatter);
            default:
                return "";
        }
    }

    public void addListener(String name, ChangeListener listener){
        this.listeners.add(listener);
    }

    public Map<String, Expression> getAllAnswers(){
        return table;
    }

    public void setExpression(String identifier, Expression value) {
        this.table.put(identifier, value);
        if(value instanceof ExpressionVariable) System.out.println("trying to set " + identifier + " with " + ((ExpressionVariable)value).value);

        // Notify listener
        System.out.println("listeners: " + listeners.size());
        System.out.println("listeners: " + listeners);
        for(ChangeListener listener : listeners){
//            listener.stateChanged(new ChangeEvent(null));
            listener.stateChanged(new ChangeEvent(new Pair(identifier, value)));
        }

//        System.out.println();
//        for(Map.Entry<String, Expression> entry : table.entrySet()){
//            System.out.println(entry.getKey() + "," + entry.getValue());
//        }
    }
}
