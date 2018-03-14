package nl.uva.js.qlparser.wrappers.logic;

import nl.uva.js.qlparser.models.ql.expressions.data.Variable;

@FunctionalInterface
public interface ValueChangeListener {
    void onChange(Variable expression);
}
