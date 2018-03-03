package org.uva.sea.ql.staticAnalysis;

import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.visitor.BaseASTVisitor;

import java.util.HashSet;

public class CheckDuplicateLabels extends BaseASTVisitor<Void> {

    private Messages errors = new Messages();

    private HashSet<String> labels;

    public Messages doTypeCheck(Form node) {
        node.accept(this);
        return this.errors;
    }


    public Void visit(Question node) {
        super.visit(node);
        return null;
    }
}
