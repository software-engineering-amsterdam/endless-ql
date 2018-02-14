package ql.ast.statement;

import java.util.ArrayList;

public class Block extends Statement {
    
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
}
