package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;

public interface IQLStaticAnalysis {
    Messages doCheck(Form node);
}
