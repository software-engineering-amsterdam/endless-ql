package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.antlr.tool.Message;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.visitor.BaseStyleASTVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckNoDuplicateQuestions extends BaseStyleASTVisitor<Void> implements IQLSStaticAnalysis {

    /**
     * Hide constructor
     */
    private CheckNoDuplicateQuestions() {

    }

    /**
     * Perform the check
     *
     * @return Warnings
     */
    public Messages doCheck(Form form, Stylesheet stylesheet) {

        List<String> qlsQuestions = getQlSQuestionNames(stylesheet);
        return checkForDuplicateQuestions(qlsQuestions);
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
     * Checks if there are duplicate questions and returns are Message
     * @param listToCheck Question list
     * @return Messages
     */
    public Messages checkForDuplicateQuestions(List<String> listToCheck)
    {
        Messages messages = new Messages();
        Set<String> validationSet = new HashSet();

        for (String name : listToCheck)
        {
            if (!validationSet.add(name))
                messages.addMessage("duplicate question: " + name, MessageTypes.ERROR);
        }

        return messages;
    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IQLSStaticAnalysis {
        @Override
        public Messages doCheck(Form form, Stylesheet stylesheet) {
            IQLSStaticAnalysis checker = new CheckNoDuplicateQuestions();
            return checker.doCheck(form, stylesheet);
        }
    }
}
