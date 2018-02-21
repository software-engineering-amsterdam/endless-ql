package qlviz.interpreter;

import qlviz.QLParser;
import qlviz.model.Form;

public class TreeToFormConverter implements ITreeToFormConverter {

    private final FormVisitor formVisitor;

    public TreeToFormConverter(FormVisitor formVisitor) {
        this.formVisitor = formVisitor;
    }


    @Override
    public Form Convert(QLParser parser) {
		return formVisitor.visit(parser.form());
    }
}
