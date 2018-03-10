package ql.ast;

import java.util.List;

import ql.ast.statement.Statement;
import ql.visiting.StatementVisitor;


public class Block extends AstNode {

	private List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}

	public Block() {

	}

    public void insertStatement(Statement statement){
        this.statements.add(statement);
    }
    
    public List<Statement> getStatements(){
        return this.statements;
    }
    
	public <T, U> T accept(StatementVisitor<T, U> visitor, U ctx){
		return visitor.visit(this, ctx);
    }
}
