package org.uva.sea.gui.model.factory;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.*;
import org.uva.sea.languages.ql.parser.NodeType;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DefaultValueFactory {

    private final Map<NodeType, Value> defaultValues = new HashMap<>();

    public DefaultValueFactory() {
        this.defaultValues.put(NodeType.BOOLEAN, new BooleanValue(false));
        this.defaultValues.put(NodeType.DECIMAL, new DecimalValue(0.0));
        this.defaultValues.put(NodeType.INTEGER, new IntValue(0));
        this.defaultValues.put(NodeType.MONEY_EURO, new MoneyValue(MoneyType.MoneyType_Euro, new BigDecimal(0)));
        this.defaultValues.put(NodeType.MONEY_DOLLAR, new MoneyValue(MoneyType.MoneyType_Dollar, new BigDecimal(0)));
        this.defaultValues.put(NodeType.DATE, new DateValue(Calendar.getInstance()));
        this.defaultValues.put(NodeType.STRING, new StringValue(""));
    }

    public Value getDefaultValue(NodeType nodeType) {
        try {
            Value value = this.defaultValues.get(nodeType);
            if(value == null) {
                return new ErrorValue("No default style available for " + nodeType, 0, 0);
            }
            return (Value) value.clone();
        } catch (CloneNotSupportedException e) {
            return new ErrorValue("No default style available for " + nodeType, 0, 0);
        }
    }
}
