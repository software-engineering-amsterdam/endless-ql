package org.uva.jomi.ql.ast;

import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ql.ast.statements.StatementVisitor;
import org.uva.jomi.ql.parser.antlr.*;
import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends QLBaseVisitor<List<Statement>> {
	private final StatementVisitor statementVisitor;

	public AstBuilder(boolean printErros) {
		statementVisitor = new StatementVisitor(printErros);
	}

	public int getNumberOfErros() {
		return statementVisitor.getNumberOfErrors();
	}

	@Override public List<Statement> visitParse(QLParser.ParseContext ctx) {
		List<Statement> statements = new ArrayList<>(ctx.formStatement().size());
		for (QLParser.FormStatementContext statement : ctx.formStatement()) {
			statements.add(statement.accept(statementVisitor));
		}

		return statements;
	}
}

