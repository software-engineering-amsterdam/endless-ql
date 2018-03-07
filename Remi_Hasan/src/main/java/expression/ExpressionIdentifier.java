package expression;

import analysis.SymbolTable;

//public class ExpressionIdentifier extends Expression {
//
//    private final String identifier;
//
//    public ExpressionIdentifier(String identifier) {
//        this.identifier = identifier;
//    }
//
//    @Override
//    public String toString() {
//        return identifier;
//    }
//
//    @Override
//    public ReturnType getReturnType(SymbolTable symbolTable) {
//        return symbolTable.getExpression(this.identifier).getReturnType(symbolTable);
//    }
//
//    @Override
//    public ExpressionVariable evaluate(SymbolTable symbolTable) {
//        return symbolTable.getExpression(this.identifier).evaluate(symbolTable);
//    }
//
//    @Override
//    public void typeCheck(SymbolTable symbolTable) { }
//}
