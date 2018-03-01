import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import nl.khonraad.ExpressionLanguageLexer;
import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.MyVisitor;
import nl.khonraad.Question;

public class ExpressionLanguageVisitorTest {

	@Test
	public void testExample() throws Exception {
		
		InputStream stream = getClass().getResourceAsStream("/example.input");

		ExpressionLanguageLexer expressionLanguageLexer = new ExpressionLanguageLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
	
		ExpressionLanguageParser expressionLanguageParser = new ExpressionLanguageParser( new CommonTokenStream(expressionLanguageLexer));
		
		expressionLanguageParser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		
		ParseTree parseTree = expressionLanguageParser.form();

		System.out.println(parseTree.toStringTree(expressionLanguageParser));
		
		MyVisitor myVisit  = new MyVisitor();
		
		myVisit.visit(parseTree);
		
		// simulate answer given
		for (Map.Entry<String,Question> entry : myVisit.questions.entrySet())
		{
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		System.out.println("------------------------");
		
		// simulate answers given
		myVisit.questions.get("sellingPrice").setValue("100");
		myVisit.questions.get("privateDebt").setValue("25");
		
		myVisit.visit(parseTree);
		
		// show situtation
		System.out.println(myVisit.questions.get("valueResidue").getValue());
		
		
	}
}