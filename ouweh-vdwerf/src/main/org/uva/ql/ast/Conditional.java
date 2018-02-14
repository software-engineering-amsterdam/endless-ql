package main.org.uva.ql.ast;

public class Conditional extends Statement{

    private Expression condition;
    private Statement [] ifSide;
    private Statement [] elseSide;

}
