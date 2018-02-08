package nl.uva.se.sc.niro;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ql.QLBaseListener;
import ql.QLLexer;
import ql.QLParser;
import ql.QLParser.ExpressionContext;
import ql.QLParser.FormContext;

/**
 * Just for testing the ANTLR integration. Acts mainly for Proof Of Concept. 
 * @author Nico Tromp
 */
public class ListeningCompiler extends QLBaseListener {

	public Object compileScriptFile(CharStream source) throws IOException {
		QLLexer lexer = new QLLexer(source);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		QLParser parser = new QLParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ErrorListener());

		FormContext form = parser.form();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(this, form);
		return null;
	}
	
	@Override
	public void enterExpression(ExpressionContext ctx) {
		switch (ctx.getChildCount()) {
		case 1 :
			System.out.printf("EXPR=[%s]%n", ctx.getChild(0).getText());
			break;
		case 2 :
			System.out.printf("OP=[%s] EXPR=[%s]%n", ctx.getChild(0).getText(), ctx.getChild(1).getText());
			break;
		case 3 :
			System.out.printf("LHS=[%s] OP=[%s] RHS=[%s]%n", ctx.getChild(0).getText(), ctx.getChild(1).getText(), ctx.getChild(2).getText());
			break;
		}
	}
		
}
