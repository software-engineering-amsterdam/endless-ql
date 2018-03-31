package com.chariotit.uva.sc.qdsl.ast.qls;

import com.chariotit.uva.sc.qdsl.ast.ql.node.QLAstRoot;
import com.chariotit.uva.sc.qdsl.ast.common.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Stylesheet;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.TypeCheckVisitor;

import java.util.List;

public class Validator {

    private QLAstRoot astRoot;

    public Validator(QLAstRoot astRoot) {
        this.astRoot = astRoot;
    }

    public List<TypeCheckError> typeCheckQLS(Stylesheet stylesheet) {

        TypeCheckVisitor visitor = new TypeCheckVisitor(astRoot.getQuestionSymbolTable());
        stylesheet.acceptVisitor(visitor);

        return visitor.getErrors();
    }

}
