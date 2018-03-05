package org.uva.sea.ql.staticAnalysis;

import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.staticAnalysis.helpers.Messages;

public interface IStaticAnalysis {
    Messages doCheck(Form node);
}
