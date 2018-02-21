package ql.ast.statement;

import java.util.ArrayList;

import ql.visitors.interfaces.ExpressionVisitable;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.StatementVisitor;

public class Block extends Statement implements ExpressionVisitable {
    
    private ArrayList<Statement> statements;
    
    public Block()
    {
        this.statements = new ArrayList<Statement>();
    }
    
    public Block(Statement statement)
    {
        this.statements = new ArrayList<Statement>();
        addStatement(statement);
    }
    
    public Block(ArrayList<Statement> statements)
    {
        this.statements = statements;
    }
    
    public void addStatement(Statement statement)
    {
        this.statements.add(statement);
    }
    
    public ArrayList<Statement> getStatements()
    {
        return this.statements;
    }

    @Override
    public String toString() {
        
        String str = "{ ";
        
        for(Statement stmt : statements)
        {
            str += "[";
            str += stmt.toString();
            str += "] ";
        }
                
        str += "}";
        
        return str;
    }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
