package nl.uva.se.sc.niro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Ast.AnswerType;
import model.Ast.AnswerType$;
import model.Ast.QLForm;
import model.Ast.Question;
import model.Ast.Statement;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ql.QLBaseListener;
import ql.QLLexer;
import ql.QLParser;
import ql.QLParser.ExpressionContext;
import ql.QLParser.FormContext;
import scala.collection.JavaConverters;
import scala.collection.Seq;

/**
 * Just for testing the ANTLR integration. Acts mainly for Proof Of Concept. 
 * @author Nico Tromp
 */
public class ListeningCompiler extends QLBaseListener {

	private QLForm qlForm;

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

	@Override
	public void enterForm(FormContext ctx) {
		List<Statement> jList = new ArrayList<>();
		Seq<Statement> sList = JavaConverters.asScalaBufferConverter(jList).asScala().toSeq();

		qlForm = new QLForm(ctx.name().getText(), sList);
		System.out.printf("%nform %s {%n", ctx.name().getText());
	}

	@Override
	public void enterQuestion(QLParser.QuestionContext ctx) {
		String questionId = ctx.name().getText();
		String questionLabel = ctx.children.get(2).getText();
		String answerTypeText = ctx.answer_type().getText();
		AnswerType answerType = AnswerType$.MODULE$.apply(answerTypeText);

		Question question = new Question(questionId, questionLabel, answerType);
		List<Statement> questions = JavaConverters.seqAsJavaList(qlForm.statements());
		questions.add(question);
		Seq<Statement> sList = JavaConverters.asScalaBufferConverter(questions).asScala().toSeq();

		qlForm = new QLForm(qlForm.formName(), sList);
		System.out.printf("\t%s : %s %s%n", ctx.name().getText(), ctx.TEXT().getText(), ctx.answer_type().getText());
	}
}
