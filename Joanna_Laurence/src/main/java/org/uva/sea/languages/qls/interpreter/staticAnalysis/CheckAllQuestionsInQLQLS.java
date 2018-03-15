package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class CheckAllQuestionsInQLQLS extends BaseStyleASTVisitor<Void> implements IQLSStaticAnalysis {

    /**
     * Hide constructor
     */
    private CheckAllQuestionsInQLQLS() {

    }

    /**
     * Perform the check
     *
     * @return Warnings
     */
    public Messages doCheck(Form form, Stylesheet stylesheet) {

        Messages messages = new Messages();

        List<String> qlQuestions = getQlQuestionNames(form);
        List<String> qlsQuestions = getQlSQuestionNames(stylesheet);
        messages.addMessageList(checkListsEqual(qlQuestions, qlsQuestions, "QLS misses question: "));
        messages.addMessageList(checkListsEqual(qlsQuestions, qlQuestions, "QL misses question: "));

        return messages;
    }

    /**
     *
     * @param firstList
     * @param secondList
     * @param errorMessage
     * @return
     */
    private Messages checkListsEqual(List<String> firstList, List<String> secondList, String errorMessage) {
        Messages messages = new Messages();

        List<String> difference = new ArrayList<>(firstList);
        difference.removeAll(secondList);
        for(String question : difference) {
            messages.addMessage(errorMessage + question, MessageTypes.ERROR);
        }

        return messages;
    }

    /**
     * Get all QL question names
     * @param form AST node
     * @return The names
     */
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

    /**
     * Get all QLS question names
     * @param stylesheet AST node
     * @return The names
     */
    private List<String> getQlSQuestionNames(Stylesheet stylesheet) {
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

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IQLSStaticAnalysis {
        @Override
        public Messages doCheck(Form form, Stylesheet stylesheet) {
            IQLSStaticAnalysis checker = new CheckAllQuestionsInQLQLS();
            return checker.doCheck(form, stylesheet);
        }
    }
}
