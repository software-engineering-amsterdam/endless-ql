package org.uva.sea.ql.staticAnalysis;

import org.uva.sea.ql.dataObject.MessageTypes;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.staticAnalysis.helpers.Messages;

public class CheckIncorrectDuplicateQuestions implements IStaticAnalysis {
    @Override
    public Messages doCheck(Form node) {
        return new Messages(MessageTypes.ERROR);
    }
}
