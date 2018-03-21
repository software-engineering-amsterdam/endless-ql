package org.uva.sea.languages.qls.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

public interface IQLSStaticAnalysis {
    Messages doCheck(Form node, Stylesheet stylesheet);
}
