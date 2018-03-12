package org.uva.jomi.qls.ast.statements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;

import org.uva.jomi.qls.parser.antlr.QLSParser.CommandContext;
import org.uva.jomi.qls.ast.QLSToken;
import org.uva.jomi.qls.ast.statements.Stmt;
import org.uva.jomi.qls.ast.statements.widget.CheckboxWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.DropdownWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.RadioWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.SpinboxWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.WidgetStmt;
import org.uva.jomi.qls.parser.antlr.QLSBaseVisitor;
import org.uva.jomi.qls.parser.antlr.QLSParser;
import org.uva.jomi.qls.parser.antlr.QLSParser.PageStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.QuestionStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.SectionStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.StylesheetStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.WidgetCheckboxStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.WidgetDropdownStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.WidgetRadioStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.WidgetSpinboxStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.WidgetTextStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.WidgetYesNoDropdownStmtContext;
import org.uva.jomi.qls.parser.antlr.QLSParser.WidgetYesNoRadiosStmtContext;

public class StmtVisitor extends QLSBaseVisitor<Stmt> {
	
	private class BlockStmtVisitor extends QLSBaseVisitor<BlockStmt> {

		private final StmtVisitor stmtVisitor;

		public BlockStmtVisitor(StmtVisitor stmtVisitor) {
			this.stmtVisitor = stmtVisitor;
		}

		// Builds a Block statement using the parser context.
		@Override public BlockStmt visitBlockStmt(QLSParser.BlockStmtContext ctx) {
			List<Stmt> statements = new ArrayList<>(ctx.command().size());

			// Visit every statement in the block and add it to the statements array.
			for (CommandContext statement : ctx.command()) {
				statements.add(statement.accept(stmtVisitor));
			}

			return new BlockStmt(statements);
		}
	}
	
	private class WidgetVisitor extends QLSBaseVisitor<WidgetStmt> {
		private final StmtVisitor stmtVisitor;
		
		public WidgetVisitor(StmtVisitor stmtVisitor) {
			this.stmtVisitor = stmtVisitor;
		}
		
		@Override
		public WidgetStmt visitWidgetRadioStmt(WidgetRadioStmtContext ctx) {
			if (ctx.LABEL().size() == 2) {
				return new RadioWidgetStmt(ctx.LABEL(0).getText(), ctx.LABEL(1).getText());
			}
			return new RadioWidgetStmt();
		}
		
		@Override
		public WidgetStmt visitWidgetSpinboxStmt(WidgetSpinboxStmtContext ctx) {
			return new SpinboxWidgetStmt();
		}
		
		@Override
		public WidgetStmt visitWidgetTextStmt(WidgetTextStmtContext ctx) {
			return new SpinboxWidgetStmt();
		}
		
		@Override
		public WidgetStmt visitWidgetYesNoRadiosStmt(WidgetYesNoRadiosStmtContext ctx) {
			return new RadioWidgetStmt();
		}
		
		@Override
		public WidgetStmt visitWidgetCheckboxStmt(WidgetCheckboxStmtContext ctx) {
			return new CheckboxWidgetStmt();
		}
		
		@Override
		public WidgetStmt visitWidgetDropdownStmt(WidgetDropdownStmtContext ctx) {
			List<String> options = new ArrayList<String>();
			for(TerminalNode label : ctx.LABEL()) {
				options.add(label.getText());
			}
			return new DropdownWidgetStmt(options);
		}
		
		@Override
		public WidgetStmt visitWidgetYesNoDropdownStmt(WidgetYesNoDropdownStmtContext ctx) {
			List<String> options = new ArrayList<String>();
			options.add("Yes");
			options.add("No");
			return new DropdownWidgetStmt(options);
		}		
		
	}
	
	// An expression visitor is needed in order to visit the expression nodes in the Ast.
	private final BlockStmtVisitor blockStmtVisitor;

	// The expression visitor is initialized in the default constructor
	public StmtVisitor() {
		this.blockStmtVisitor = new BlockStmtVisitor(this);
	}
	
	
	
	@Override
	public Stmt visitStylesheetStmt(StylesheetStmtContext ctx) {
		IdentifierExpr identifier = new IdentifierExpr(new QLSToken(ctx.IDENTIFIER().getSymbol()));
		List<Stmt> pageStatements = new ArrayList<Stmt>();
		for (PageStmtContext page : ctx.pageStmt()) {
			pageStatements.add(page.accept(new StmtVisitor()));
		}
		
		return new StylesheetStmt(identifier, pageStatements);
	}
	
	@Override
	public Stmt visitPageStmt(PageStmtContext ctx) {
		IdentifierExpr identifier = new IdentifierExpr(new QLSToken(ctx.IDENTIFIER().getSymbol()));
		BlockStmt blockStmt = ctx.blockStmt().accept(blockStmtVisitor);
		
		return new PageStmt(identifier, blockStmt);
	}
	
	@Override
	public Stmt visitSectionStmt(SectionStmtContext ctx) {
		QLSToken name = new QLSToken(ctx.LABEL().getSymbol());
		BlockStmt blockStmt = ctx.blockStmt().accept(blockStmtVisitor);
		
		return new SectionStmt(name, blockStmt);
	}
	
	@Override
	public Stmt visitQuestionStmt(QuestionStmtContext ctx) {
		IdentifierExpr identifier = new IdentifierExpr(new QLSToken(ctx.IDENTIFIER().getSymbol()));
		
		if(ctx.widgetStmt() != null) {
			return new QuestionStmt(identifier, (WidgetStmt)ctx.widgetStmt().accept(new WidgetVisitor(this)));
		}
		
		return new QuestionStmt(identifier);
	}
	
	
}
