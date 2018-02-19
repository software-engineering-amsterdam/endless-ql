package ast;

import java.util.List;

import ast.statement.Statement;
//import visiting.QLTreeVisitor;
import visiting.StatementVisitor;


public class Block extends AstNode {

	private List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}

	public Block() {
		// TODO Auto-generated constructor stub
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
