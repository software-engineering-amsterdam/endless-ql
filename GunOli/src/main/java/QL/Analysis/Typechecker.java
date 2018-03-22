package QL.Analysis;

import QL.ParseObjectsQL.Form;
import QL.ParseObjectsQL.Question;
import QL.QLVisitor.ExpressionTable;

public class Typechecker {

    private final Form form;
    private final ExpressionTable expressionTable;

    public Typechecker(Form form, ExpressionTable expressionTable){
        this.form = form;
        this.expressionTable = expressionTable;
    }
}
