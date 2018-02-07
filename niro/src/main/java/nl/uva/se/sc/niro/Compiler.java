package nl.uva.se.sc.niro;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ql.QLBaseListener;
import ql.QLLexer;
import ql.QLParser;
import ql.QLParser.FormContext;
import ql.QLParser.NameContext;
import ql.QLParser.QuestionContext;

/**
 * Just for testing the ANTLR integration. Acts mainly for Proof Of Concept. 
 * @author Nico Tromp
 */
public class Compiler extends QLBaseListener {

	public Object compileScriptFile(String fileName) throws IOException {
		QLLexer lexer = new QLLexer(CharStreams.fromFileName(fileName));
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
	public void enterForm(FormContext ctx) {
		System.out.printf("form %s {%n", ctx.name().getText());
	}
	
	@Override
	public void exitForm(FormContext ctx) {
		System.out.printf("}%n");
	}
	
	@Override
	public void enterQuestion(QuestionContext ctx) {
		System.out.printf("\t%s : %s %s%n", ctx.name().getText(), ctx.TEXT().getText(), ctx.type().getText());
	}
	
}
