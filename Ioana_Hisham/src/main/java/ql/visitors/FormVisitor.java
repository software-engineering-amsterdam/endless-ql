package ql.visitors;

import ql.ast.Form;

public interface FormVisitor {
    public void visit(Form form);
}
