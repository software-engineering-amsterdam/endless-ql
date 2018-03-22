package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.HashSet;
import java.util.Set;

public class LabelExpression extends Expression {

    private String label;

    public LabelExpression(String label, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);

        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void evaluate(SymbolTable symbolTable) {
        SymbolTableEntry symbolTableEntry = symbolTable.getEntry(label);

        this.setExpressionValue(symbolTableEntry.getExpressionValue());
        this.setExpressionType(symbolTableEntry.getExpressionType());
    }

    @Override
    public Set<String> getPrerequisites() {
        Set<String> set = new HashSet<>();
        set.add(label);
        return set;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitLabelExpression(this);
    }
}
