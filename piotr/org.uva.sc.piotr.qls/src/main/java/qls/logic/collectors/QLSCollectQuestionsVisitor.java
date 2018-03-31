package qls.logic.collectors;

import qls.ast.model.QuestionDefinition;
import qls.ast.model.Stylesheet;
import qls.ast.visitors.AbstractASTTraverse;

import java.util.ArrayList;
import java.util.List;

public class QLSCollectQuestionsVisitor extends AbstractASTTraverse<Void> {

    private List<String> questions;

    public List<String> getQuestionsNames(Stylesheet stylesheet) {
        this.questions = new ArrayList<>();
        stylesheet.accept(this);
        return this.questions;
    }

    @Override
    public Void visit(QuestionDefinition questionDefinition) {
        this.questions.add(questionDefinition.getName());
        return null;
    }
}
