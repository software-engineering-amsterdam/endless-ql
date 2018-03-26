package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CheckAllQuestionsInQLQLS extends QuestionAnalysis implements IQLSStaticAnalysis {

    private CheckAllQuestionsInQLQLS() {

    }

    public Messages doCheck(final Form form, final Stylesheet stylesheet) {

        final Messages messages = new Messages();

        final List<String> qlQuestions = this.getQlQuestionNames(form);
        final List<String> qlsQuestions = this.getQlSQuestionNames(stylesheet);
        messages.addMessages(this.checkListsEqual(qlQuestions, qlsQuestions, "QLS misses question: "));
        messages.addMessages(this.checkListsEqual(qlsQuestions, qlQuestions, "QL misses question: "));

        return messages;
    }

    private Messages checkListsEqual(final List<String> firstList, final Collection<String> secondList, final String errorMessage) {
        final Messages messages = new Messages();

        final Collection<String> difference = new ArrayList<>(firstList);
        difference.removeAll(secondList);
        for (final String question : difference) {
            messages.addMessage(errorMessage + question, MessageTypes.ERROR);
        }

        return messages;
    }

    private List<String> getQlQuestionNames(final Form form) {
        final List<String> questionNames = new ArrayList<>();
        form.accept(new BaseASTVisitor<Void>() {
            @Override
            public Void visit(final org.uva.sea.languages.ql.parser.elements.Question node) {
                questionNames.add(node.getVariable().getVariableName());
                return super.visit(node);
            }
        });
        return questionNames;
    }

    public static class Checker implements IQLSStaticAnalysis {
        @Override
        public final Messages doCheck(final Form form, final Stylesheet stylesheet) {
            final IQLSStaticAnalysis checker = new CheckAllQuestionsInQLQLS();
            return checker.doCheck(form, stylesheet);
        }
    }
}
