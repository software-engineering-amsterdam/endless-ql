package qlviz.interpreter;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import qlviz.QLParser;
import qlviz.model.Form;

public class TreeToFormConverter implements ITreeToFormConverter {
    @Override
    public Form Convert(QLParser parser) {
        ParseTreeWalker TreeWalker = new ParseTreeWalker();
		FormListener listener = new FormListener();
		TreeWalker.walk(listener, parser.questionName());

		return new Form(listener.getQuestions());
    }
}
