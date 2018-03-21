package qlviz.gui;

import java.io.IOException;

import com.google.inject.Inject;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import qlviz.*;
import qlviz.interpreter.FormVisitor;
import qlviz.interpreter.linker.QuestionLinker;
import qlviz.interpreter.style.StylesheetVisitor;
import qlviz.model.Form;
import qlviz.model.style.Stylesheet;

public class ModelBuilder {

	private final QLBaseVisitor<Form> formParser;
	private final QuestionLinker questionLinker;

	@Inject
	public ModelBuilder(QLBaseVisitor<Form> formParser, QuestionLinker questionLinker) {
		this.formParser = formParser;
		this.questionLinker = questionLinker;
	}


	public Form createFormFromMarkup(String path){
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
		return form;
	}


	public void linkQuestions(Form form){
		questionLinker.linkQuestionStubs(form);
	}



}
