package org.uva.sea.staticAnalysis;

import org.uva.sea.ql.elements.Form;
import org.uva.sea.staticAnalysis.helpers.Messages;

public interface IStaticAnalysis {
    Messages doCheck(Form node);
}
