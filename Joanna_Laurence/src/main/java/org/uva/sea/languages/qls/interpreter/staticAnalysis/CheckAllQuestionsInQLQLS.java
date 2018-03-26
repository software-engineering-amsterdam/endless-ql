package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CheckAllQuestionsInQLQLS extends QuestionAnalysis implements IQLSStaticAnalysis {

    private CheckAllQuestionsInQLQLS() {

    }

    public Messages doCheck(Form form, Stylesheet stylesheet) {

        Messages messages = new Messages();

        List<String> qlQuestions = this.getQlQuestionNames(form);
        List<String> qlsQuestions = this.getQlSQuestionNames(stylesheet);
        messages.addMessages(this.checkListsEqual(qlQuestions, qlsQuestions, "QLS misses question: "));
        messages.addMessages(this.checkListsEqual(qlsQuestions, qlQuestions, "QL misses question: "));

        return messages;
    }

    private Messages checkListsEqual(List<String> firstList, Collection<String> secondList, String errorMessage) {
        Messages messages = new Messages();

        Collection<String> difference = new ArrayList<>(firstList);
        difference.removeAll(secondList);
        for (String question : difference) {
            messages.addMessage(errorMessage + question, MessageTypes.ERROR);
        }

        return messages;
    }

    private List<String> getQlQuestionNames(Form form) {
        List<String> questionNames = new ArrayList<>();
        form.accept(new BaseASTVisitor<Void>() {
            @Override
            public Void visit(org.uva.sea.languages.ql.parser.elements.Question node) {
                questionNames.add(node.getVariable().getVariableName());
                return super.visit(node);
            }
        });
        return questionNames;
    }

    public static class Checker implements IQLSStaticAnalysis {
        @Override
        public Messages doCheck(Form form, Stylesheet stylesheet) {
            IQLSStaticAnalysis checker = new CheckAllQuestionsInQLQLS();
            return checker.doCheck(form, stylesheet);
        }
    }
}
