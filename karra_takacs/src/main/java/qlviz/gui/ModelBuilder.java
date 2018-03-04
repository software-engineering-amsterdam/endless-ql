package qlviz.gui;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import qlviz.QLLexer;
import qlviz.QLParser;
import qlviz.interpreter.FormVisitor;
import qlviz.interpreter.linker.QuestionLinker;
import qlviz.model.Form;

public class ModelBuilder {

	private final FormVisitor formParser;
	private final QuestionLinker questionLinker;


	public ModelBuilder(FormVisitor formParser, QuestionLinker questionLinker) {
		this.formParser = formParser;
		this.questionLinker = questionLinker;
	}


	public Form createFormFromMarkup(String path) {
		CharStream charStream = null;
		try {
			charStream = new FileReader(path).getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		QLLexer lexer = new QLLexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);
		Form form = formParser.visitForm(parser.form());
		questionLinker.linkQuestionStubs(form);
		return form;
	}



}
