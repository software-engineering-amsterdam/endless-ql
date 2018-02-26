package qlviz.interpreter;

import qlviz.QLParser;
import qlviz.model.Form;

public class TreeToFormConverter implements ITreeToFormConverter {

    private final FormVisitor formVisitor;
    private final QuestionLinker linker;

    public TreeToFormConverter(FormVisitor formVisitor, QuestionLinker linker) {
        this.formVisitor = formVisitor;
        this.linker = linker;
    }


    @Override
    public Form Convert(QLParser parser) {
		Form form = formVisitor.visit(parser.form());
	    this.linker.linkQuestionStubs(form);
	    return form;
    }
}
