package gui.widgets2;

import ql.analysis.SymbolTable;
import ql.model.expression.variable.ExpressionVariableString;

import java.awt.*;

public class StringWidget extends TextField {

    private final ExpressionVariableString expression;
    private SymbolTable symbolTable = null;

    StringWidget(ExpressionVariableString expression) {
        this.expression = expression;
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        symbolTable.setExpression("naam", new ExpressionVariableString(null, t));
    }
}
