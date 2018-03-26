package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnalysis {
    protected List<String> getQlSQuestionNames(Stylesheet stylesheet) {
        List<String> questionNames = new ArrayList<>();
        stylesheet.accept(new BaseStyleASTVisitor<Void>() {
            @Override
            public Void visit(Question node) {
                questionNames.add(node.getName());
                return super.visit(node);
            }
        });
        return questionNames;
    }
}
