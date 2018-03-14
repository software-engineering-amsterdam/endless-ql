package QL.ParseObjectsQL;

import QL.QLVisitor.ExpressionTable;

public class Form {
    private String name;
    private Block block;
    private ExpressionTable expressionTable;

    public Form(String s, Block b, ExpressionTable exprTable){
        setName(s);
        setBlock(b);
        setExpressionTable(exprTable);
    }

    public void setExpressionTable(ExpressionTable exprTable){
        this.expressionTable = exprTable;
    }

    public ExpressionTable getExpressionTable(){
        return expressionTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

}