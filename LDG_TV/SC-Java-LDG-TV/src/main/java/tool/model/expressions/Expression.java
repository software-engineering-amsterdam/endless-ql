package tool.model.expressions;

import tool.model.variables.Variable;

public abstract class Expression {
    private Expression lefthand;
    private Expression righthand;
    private String opperator;

    public abstract Variable solve();
}
