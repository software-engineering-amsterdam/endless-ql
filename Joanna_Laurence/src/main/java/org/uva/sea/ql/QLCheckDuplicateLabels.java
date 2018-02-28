package org.uva.sea.ql;

import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.visitor.BaseVisitor;

import java.util.HashSet;
import java.util.List;

public class QLCheckDuplicateLabels extends BaseVisitor<Void> {

    private Errors errors = new Errors();

    private HashSet<String> labels;

    public Errors doTypeCheck(Form node) {
        node.accept(this);
        return this.errors;
    }


    public Void visit(Question node) {
        super.visit(node);


    }
}
