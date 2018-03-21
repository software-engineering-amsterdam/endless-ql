package qlviz.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import com.google.inject.Inject;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import qlviz.*;
import qlviz.interpreter.FormVisitor;
import qlviz.interpreter.linker.QuestionLinker;
import qlviz.interpreter.style.StylesheetVisitor;
import qlviz.model.Form;
import qlviz.model.style.Stylesheet;
import qlviz.typecheker.AnalysisResult;
import qlviz.typecheker.Severity;

public class ModelBuilder {

	private final QLVisitor<Form> formParser;
	private final QuestionLinker questionLinker;

	@Inject
	public ModelBuilder(QLVisitor<Form> formParser, QuestionLinker questionLinker) {
		this.formParser = formParser;
		this.questionLinker = questionLinker;
	}


	public Form parseForm(String path) throws ParserException {
		CharStream charStream = null;
		try {
			charStream = new FileReader(path).getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		QLLexer lexer = new QLLexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);

		List<AnalysisResult> parserErrors = new ArrayList<>();

		parser.addErrorListener(new ANTLRErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
				parserErrors.add(new SyntaxErrorResult(i, i1, s));
			}

			@Override
			public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {

			}

			@Override
			public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {

			}

			@Override
			public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {

			}
		});
        QLParser.FormContext context = parser.form();

		if (parserErrors.size() > 0) {
		    throw new ParserException(parserErrors);
        }

        Form form = formParser.visitForm(context);
		return form;
	}


	public void linkQuestions(Form form){
		questionLinker.linkQuestionStubs(form);
	}



}
