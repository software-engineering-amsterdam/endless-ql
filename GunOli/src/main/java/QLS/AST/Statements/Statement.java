package QLS.AST.Statements;

import QLS.AST.QLSNode;

public abstract class Statement extends QLSNode {
    public Statement(int line) {
        super(line);
    }
}
