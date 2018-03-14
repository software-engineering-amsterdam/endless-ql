package nl.uva.js.qlparser.wrappers.logic;

import nl.uva.js.qlparser.models.expressions.data.DataExpression;

@FunctionalInterface
public interface ValueChangeListener {
    void onChange(DataExpression expression);
}
