package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CheckNoDuplicateQuestions extends QuestionAnalysis implements IQLSStaticAnalysis {

    public final Messages doCheck(final Form form, final Stylesheet stylesheet) {
        final List<String> qlsQuestions = this.getQlSQuestionNames(stylesheet);
        return this.checkForDuplicateQuestions(qlsQuestions);
    }


    private Messages checkForDuplicateQuestions(final Iterable<String> listToCheck) {
        final Messages messages = new Messages();
        final Collection<String> validationSet = new HashSet();

        for (final String name : listToCheck) {
            if (!validationSet.add(name))
                messages.addMessage("duplicate question: " + name, MessageTypes.ERROR);
        }

        return messages;
    }

    public static class Checker implements IQLSStaticAnalysis {
        @Override
        public final Messages doCheck(final Form form, final Stylesheet stylesheet) {
            final IQLSStaticAnalysis checker = new CheckNoDuplicateQuestions();
            return checker.doCheck(form, stylesheet);
        }
    }
}
