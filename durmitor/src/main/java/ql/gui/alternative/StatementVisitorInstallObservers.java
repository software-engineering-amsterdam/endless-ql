package ql.gui.alternative;

import ql.ast.expression.Identifier;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.visitors.checker.checkers.ExpressionVisitorIdentifier;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorInstallObservers implements StatementVisitor {

	@Override
	public void visit(Block block) {
		for(Statement stmt : block.getStatements()) stmt.accept(this);
	}

	@Override
	public void visit(IfThen stmt) {
		
		for(Identifier observable : stmt.getCondition().accept(new ExpressionVisitorIdentifier()))
		{
			observable.addObserver(stmt);
		}
	}

	@Override
	public void visit(IfThenElse stmt) {
		
		for(Identifier observable : stmt.getCondition().accept(new ExpressionVisitorIdentifier()))
		{
			observable.addObserver(stmt);
		}
	}

	@Override
	public void visit(AnswerableQuestion stmt) {
	}

	@Override
	public void visit(ComputedQuestion stmt) {
		
		for(Identifier observable : stmt.getComputation().accept(new ExpressionVisitorIdentifier()))
		{
			observable.addObserver(stmt);
		}
	}
}
