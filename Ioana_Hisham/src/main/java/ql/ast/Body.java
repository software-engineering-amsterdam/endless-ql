package ql.ast;

import java.util.List;

public class Body extends Node {

    // todo: After implementing the correct QL type,
    // replace field type with the actual QL type
    List<String> statements;

    // todo: After implementing the correct QL type,
    // replace argument type(statements)  with the actual QL type
    public Body(int lineNumber, List<String> statements) {
        super(lineNumber);
        this.statements = statements;
    }

    // todo: After implementing the correct QL type,
    // replace argument type(statements)  with the actual QL type
    public List<String> getStatement() {
        return statements;
    }
}
